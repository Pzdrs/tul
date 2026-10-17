clc; clear all
% hmotnost [kg]
cyclistWeight = 70;
bicycleWeight = 15;
% počáteční rychlost cyklisty prevedeno z km/h [m/s]
initialSpeed = 30/3.6;

% parametry metod
timeStart = 0;
timeEnd = 150;
stepSize = 1;

% tolerance pro ustalenou rychlost - jak moc velka zmena mezi poslednim
% zaznamem se pocita jako prakticky stejna (ustalena)
steadyTolerance = 0.001;

% hnací síla cyklisty [N]
P = 20;
% hustota prostredi i suppose??
k = 0.516;

% diff. rovnice popisujici rychlost cyklisty
equation = @(t, v) (P - k*v^2)/(cyclistWeight + bicycleWeight);

% Explicitni eulerova metoda
[t, y] = euler_exp(equation, timeStart, timeEnd, initialSpeed, stepSize);
[velocity, time] = steadyCheck(t, y, steadyTolerance);

% cc bullshit
v_end = velocity;

fprintf('Podle explicitni Eulerovy metody se rychlost ustalila na %.5fm/s v case %.2fs\n', velocity, time)

plot(t, y, 'r--', time, velocity, 'r-o')
hold on

% Implicitni eulerova metoda
[t, y]=euler_imp(equation, timeStart, timeEnd, initialSpeed, stepSize);
[velocity, time] = steadyCheck(t, y, steadyTolerance);

% cc bullshit
v_end = velocity;

fprintf('Podle implicitni Eulerovy metody se rychlost ustalila na %.5fm/s v case %.2fs\n', velocity, time)

plot(t, y, 'g--', time, velocity, 'g-o')

% ode45  
[t, y]=ode45(equation, [timeStart timeEnd], initialSpeed);
[velocity, time] = steadyCheck(t, y, steadyTolerance);

% cc bullshit
v_end = velocity;

fprintf('Podle metody ode45 se rychlost ustalila na %.5fm/s v case %.2fs\n', velocity, time)

plot(t, y, 'b-', time, velocity, 'b-o')

legend('Explicitní Euler', 'Explicit ustálení', 'Implicitní Euler', 'Implicit ustálení', 'ode45', 'ode45 ustálení')
xlabel('čas [s]')
ylabel('rychlost [m/s]')
hold off
grid on
saveas(gcf, "graph.png")

% Najde a vrati ustalenou rychlost 
function [velocity, time] = steadyCheck(t, y, tol)
    % projede vsechny zaznamy cas/rychlost
    for i=1:length(y)-1
        % je-li ustaleno, vrat kde presne
        if abs(y(i) - y(i+1)) < tol 
            velocity = y(i);    % rychlost [m/s]
            time = t(i);        % cas [s]
            break
        end
    end
end
