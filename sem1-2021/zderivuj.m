function z=zderivuj(x,y)
n=length(x);

if length(y)~=n
    return
end
for i=1:n-1
    z(i)=(y(i+1)-y(i))/(x(i+1)-x(i));
end
z(n)=z(n-1);
end