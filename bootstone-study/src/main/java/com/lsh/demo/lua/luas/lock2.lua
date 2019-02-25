local lockkey = KEYS[1]
--唯一随机数
local uid = KEYS[2]
--失效时间，如果是当前线程，续期时间
local time = KEYS[3]

if redis.call('SET',lockkey,uid,'NX','PX', time) then
   return 'OK'
else
    if redis.call('get',lockkey) == uid then
           if redis.call('EXPIRE',lockkey,time/1000)==1 then
           return 'OOKK'
           end
        end
end