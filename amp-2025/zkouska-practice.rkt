#lang scheme

(define (f a b) (/ (+ (/ a b) (/ b a)) (* a b)))

(define (vyber n list)
  (if (> n (- (length list) 1)) '() (list-ref list n)))

(define-struct oddeleni (nazev zamestnanci))

(define podnik (list
  (make-oddeleni "Prodej"
    '("Anna Bílá" "Lída Žlutá" "Petr Modrý"))
  (make-oddeleni "Vývoj"
    '("Josef Zelený" "Lenka Růžová" "Alena Černá"))
))

(define (hledej-oddeleni name departments)
  (cond
    [(empty? departments) '()]
    [(je-z-oddeleni? name (car departments)) (oddeleni-nazev (car departments))]
    [else (hledej-oddeleni name (cdr departments))]
    ))

(define (je-z-oddeleni? name department)
  (member name (oddeleni-zamestnanci department)))

;(define (je-z-oddeleni? name department)
;  (je-zamestnanec? name (oddeleni-zamestnanci department)))

;(define (je-zamestnanec? name employees)
;  (cond
;    [(empty? employees) #f]
;    [(equal? name (car employees)) #t]
;    [else (je-zamestnanec? name (cdr employees))]
;    ))

;(hledej-oddeleni "Petr Modrý" podnik)
;(hledej-oddeleni "Lenka Zelená" podnik)
;(hledej-oddeleni "Lenka Růžová" podnik)







(define (g x y)
  (/ (+ (expt x 2) (expt y 2)) (+ x y)))

(define (vyber-posledni list)
  (list-ref list (- (length list) 1)))

(vyber-posledni '(5 10 15 20))




