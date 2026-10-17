function x = puleniintervalu(a,b,f,eps)
if f(a)*f(b)>0
    x=NaN;
    return
end
if eps <=0
    x=NaN;
    return
end
while abs(b-a)>eps
    s=(a+b)/2;
    if f(s) == 0
        x = s;
        return
    end
       if f(a)*f(s)<0
           b = s;
       else 
           a = s;
       end
       x=(a+b)/2;
    end
end