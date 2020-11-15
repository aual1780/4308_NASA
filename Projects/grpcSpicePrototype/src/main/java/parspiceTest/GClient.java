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

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A simple client that requests a greeting from the {@link GServer}.
 */
public class GClient {
    private static final Logger logger = Logger.getLogger(GClient.class.getName());

    private final Str2EtGrpc.Str2EtBlockingStub blockingStub;

    /** Construct client for accessing ParSpiceTest server using the existing channel. */
    public GClient(Channel channel) {
        blockingStub = Str2EtGrpc.newBlockingStub(channel);
    }

    /** ParSpice Str2Et */
    public double Str2Et(String name) {
        logger.info("Sending " + name + " ...");
        Str2EtReq request = Str2EtReq.newBuilder().setTime(name).build();
        Str2EtRep response;
        try {
            response = blockingStub.parStr2Et(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return 0;
        }
        logger.info("Returned: " + response.getTime());
        return response.getTime();
    }

    /**
     * Greet server. If provided, the first element of {@code args} is the name to use in the
     * greeting. The second argument is the target server.
     */
    public static void main(String[] args) throws Exception {
        String timeInput = "2004 jun 11 19:32:00";
        String target = "localhost:50051";

        // Create a communication channel to the server, known as a Channel. Channels are thread-safe
        // and reusable. It is common to create channels at the beginning of your application and reuse
        // them until the application shuts down.
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext()
                .build();
        try {
            GClient client = new GClient(channel);
            client.Str2Et(timeInput);
        } finally {
            // ManagedChannels use resources like threads and TCP connections. To prevent leaking these
            // resources the channel should be shut down when it will no longer be used. If it may be used
            // again leave it running.
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}
