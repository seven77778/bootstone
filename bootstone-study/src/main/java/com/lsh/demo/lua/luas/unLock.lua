
local lock = KEYS[1]
local uid = KEYS[2]
if redis.call('get',lock) == uid then
return redis.call('del',lock)
else
return 0
end