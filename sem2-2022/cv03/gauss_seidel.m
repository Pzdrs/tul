function [x,flag,rr,it,rv] = gauss_seidel(A,b,tol,maxit)
  x = zeros(size(b,1));
  xComponent = b;
  xOld = x;
  rv(1,1) = norm(A*xComponent - b); % Vektor reziduí
  rr = rv(end) / norm(b); % Relativní rezidium
 
  for it = 1:maxit
    
    for i = 1:size(A, 1)
      xtemp = 0;
      
      for j = 1:i-1
        xtemp = xtemp + A(i, j) * xComponent(j);
      end
   
      for j = i+1:size(A, 1)
        xtemp = xtemp + A(i, j) * xOld(j);
      end
      xComponent(i) = (1/A(i,i)) * (b(i) - xtemp);
    end
 rr = rv(end) / norm(b);
    rv = [rv; norm(A * xComponent - b)];
   
    if(isnan(rr) || isinf(rr))
      flag = 4;
      return
    end
    if(rr < tol)
      flag = 0;
      return
    end
    if(it >= maxit)
      flag = 1;
      return
    end
    x = xComponent;
    xOld = x;
  end
end
