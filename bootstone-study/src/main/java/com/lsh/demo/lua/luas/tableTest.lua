local result ={};
local mypreson = KEYS[1];
local nums = ARGV[1];

local myresult = redis.call("hkeys",mypreson);

for i,v in ipairs(myresult) do
	local hval = redis.call("hget",mypreson,v);
	redis.log(redis.LOG_WARING,hval);
	if(tonumber(hval) < tonumber(nums)) then
	table.insert(result,1,v);
	end
end

return result;
