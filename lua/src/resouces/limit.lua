-- 限制同一IP访问次数 每分钟访问10次
local key = "rate.limit:" .. KEYS[1] -- 要限制的唯一name，比如ip
local limit = tonumber(ARGV[1]) -- 次数
local expire_time = ARGV[2] -- 多久时间



local is_exists = redis.call("EXISTS", key)--
if is_exists == 1 then
    if redis.call("INCR", key) > limit then
        return "NO"
    else
        return "OK2"
    end
else
    return redis.call("SET", key, "1","NX","PX",expire_time)
end
