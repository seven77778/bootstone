local result = redis.call('get','lockkey')
local uid = KEYS[1]
if result == uid then
return 4444
else
return 8888
end
