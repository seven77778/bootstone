print("hello !!!")
a = 10 -- 全局变量
local b = 20 --局部变量
c = "str"
print(type(a)) -- type(a) 输出类型 number
print(type(2.222)) -- Lua 默认只有一种 number 类型
print(type(c)) -- string类型
print(a+1) -- 变量a无需定义类型
print("1" + 2) --进行算术操作时，String会转换数字
-- print(a+"ss") -- 报错，进行算术操作时，String会转换数字

-- if语句
if(a==10)
then
print("a = 10")
else
print("a != 10")
end
