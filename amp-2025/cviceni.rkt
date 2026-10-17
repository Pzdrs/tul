#lang scheme

(define (second L)
  (car (cdr L)))

(define (last L)
  (car (reverse L)))

(define (shortened L)
  (reverse (cdr (reverse L))))

(define (remove_second L)
  (cons (car L) (cddr L)))

(define (swap L)
  (cons (car (cdr L))
        (list (car L))))

(define (mod23? N)
  (and (= 0 (modulo N 3))
       (= 0 (modulo N 2))))


(define (omezit mini x maxi)
  (cond
    [(< x mini) mini]
    [(> x maxi) maxi]
    [else x]))

(define (list_even? L)
  (cond
    [(empty? L) #t]
    [(odd? (car L)) #f]
    [else (list_even? (cdr L))]))

(define (sum L)
  (cond
    [(empty? L) 0]
    [else (+ (car L) (sum (cdr L)))]
    )
  )

(define (split lst)
  ; lambda s hvezdickou protoze potrebuju evaluovat variables postupne jelikoz na sebe odkazujou navzajem
  (let* ((half (ceiling (/ (length lst) 2))) ; ceil nebo floor podle toho jestli chci v odd pripade mit delsi prvni nebo druhej list
         (first (take lst half))
         (second (drop lst half)))
    (list first second)))

(split '(1 2 3 4 5 6 7 8 9))