function yq = lagrange(x,y,xq)
m = 0;
for i = 1:length(x)
    r = 1;
    for j = 1:length(y)
        if j ~=i
            r = r .* (xq - x(j))/(x(i) - x(j));
        end
    end
    % pridam dalsi yq value
    m = m + y(i)*r;
end
yq = m;
end
