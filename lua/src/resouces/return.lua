local key1 = KEYS[1]
local key2 = KEYS[2]
local argv1 = ARGV[1]
return redis.call("set",key1,key2)
