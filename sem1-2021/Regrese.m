function a=Regrese(x,y,s)
A=x.^0;
for i=1:s
    A=[x.^i,A];
end
a=A\y;
end