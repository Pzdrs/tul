function a=Interpol(x,y)
A=x.^0;
n=length(x);
for i=1:n-1
    A=[x.^i,A];
end
a=A\y;
end