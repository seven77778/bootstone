local value = redis.call('get', KEYS[1]);
local result = ARGV[1];
if (value ==  result )
then
return 1 .." print:"..value.." "..KEYS[2];
else return 0 .." print:"..value.." "..KEYS[2];
end;

********************

local value = redis.call('get', KEYS[1]) local result = ARGV[1] if (value ==  result ) then return 1 .." print:"..value.." "..KEYS[2] else return 0 .." print:"..value.." "..KEYS[2] end
