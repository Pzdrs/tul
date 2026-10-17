function y = funkce_s_nargin_cv5(inX,inY)
if nargin == 1
    inY = 0;
end
y = (inX.*exp(-inX.^2-inY.^2))+tanh(inX.*inY);

end

