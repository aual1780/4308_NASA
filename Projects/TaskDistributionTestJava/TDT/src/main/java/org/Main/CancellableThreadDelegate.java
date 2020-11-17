package org.Main;

import com.tunnelvisionlabs.util.concurrent.CancellationToken;

public class CancellableThreadDelegate<T_Arg> implements Runnable
{
    private final CancellableThreadRunnable<T_Arg> _threadStartCallback;
    private final T_Arg _arg;
    private final CancellationToken _token;


    public CancellableThreadDelegate(CancellableThreadRunnable<T_Arg> threadStartCallback, T_Arg arg, CancellationToken token)
    {
        _threadStartCallback = threadStartCallback;
        _arg = arg;
        _token = token;
    }

    public void run()
    {
        _threadStartCallback.run(_arg, _token);
    }
}
