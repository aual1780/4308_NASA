/*
 * Copyright 2015 The gRPC Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package parspiceTest;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import spice.basic.CSPICE;
import spice.basic.SpiceErrorException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Server that manages startup/shutdown of a {@code Greeter} server.
 */
public class GServer {
    private static final Logger logger = Logger.getLogger(GServer.class.getName());

    private Server server;

    private void LoadSpice(){
        // replace with absolute path; find a fix for the future
        // path to the jnispice .dll
        System.load("C:/Users/nicho/Class/JNISpice/lib/JNISpice.dll");
    }

    private void start() throws IOException {
        LoadSpice();

        /* The port on which the server should run */
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new Str2EtImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                try {
                    GServer.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        /*
        LoadSpice();
        try{
            CSPICE.str2et("2004 jun 11 19:32:00");
        }
        catch(Exception e){
            System.out.println(e);
        }*/
        final GServer server = new GServer();
        server.start();
        server.blockUntilShutdown();
    }

    static class Str2EtImpl extends Str2EtGrpc.Str2EtImplBase {

        @Override
        public void parStr2Et(Str2EtReq req, StreamObserver<Str2EtRep> responseObserver) {

            double result;
            try{
                result = CSPICE.str2et(req.getTime());
            }
            catch (SpiceErrorException e){
                result = -1.0;
            }
            Str2EtRep reply = Str2EtRep.newBuilder().setTime(result).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}
