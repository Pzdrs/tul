function y=polynom(x,a)
y=0;
stupen = length(a) - 1;
for i = 1 : stupen + 1
    y = y + a(i) * x.^(stupen+1-i);
end
end