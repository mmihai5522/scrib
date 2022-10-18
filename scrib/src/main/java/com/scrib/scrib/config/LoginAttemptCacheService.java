package com.scrib.scrib.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

import static java.util.concurrent.TimeUnit.MINUTES;

@Service
public class LoginAttemptCacheService {

    private static final int MXM_ATTEMPT_NR=5;
    private static final int ATTEMPT_INCREMENT=1;

    private LoadingCache<String,Integer> attemptCache;

    public LoginAttemptCacheService() {
        super();
        this.attemptCache = CacheBuilder
                .newBuilder().expireAfterWrite(15, MINUTES)
                .maximumSize(100).build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String s) throws Exception {
                        return 0;
                    }
                });
    }

    public void evictFromCache(String username){
        attemptCache.invalidate(username);
    }

    public void addUserToCache(String username){
        int attempts=0;

        try {
            attempts =ATTEMPT_INCREMENT+ attemptCache.get(username);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        attemptCache.put(username, attempts);
    }

    public boolean hasExceededMaxAttempt(String username)  {
        try {
            return attemptCache.get(username) >= MXM_ATTEMPT_NR;
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }
}
