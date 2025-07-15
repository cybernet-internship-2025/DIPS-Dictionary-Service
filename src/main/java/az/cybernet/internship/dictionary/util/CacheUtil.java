package az.cybernet.internship.dictionary.util;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.redisson.api.RBucket;
import org.redisson.api.RKeys;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class CacheUtil {
    RedissonClient redissonClient;

    public <T> T getBucket(String cacheKey) {
        RBucket<T> bucket = redissonClient.getBucket(cacheKey);
        return bucket == null ? null : bucket.get();
    }

    public <T> void saveToCache(String key,
                                T value,
                                Long expireTime,
                                TemporalUnit temporalUnit) {
        var bucket = redissonClient.getBucket(key);
        bucket.set(value);
        bucket.expire(Duration.of(expireTime, temporalUnit));
    }

    public void delete(String key) {
        redissonClient.getBucket(key).delete();
    }

    public void deleteByPrefix(String prefix) {
        RKeys keys = redissonClient.getKeys();

        Iterable<String> keyIterable = keys.getKeysByPattern(prefix + "*");
        List<String> matchedKeys = StreamSupport.stream(keyIterable.spliterator(), false)
                .toList();

        if (!matchedKeys.isEmpty()) {
            keys.delete(matchedKeys.toArray(new String[0]));
        }
    }
}