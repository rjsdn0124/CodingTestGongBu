def solution(numbers):
    answer = 0
    l = len(numbers)
    bm = [0 for _ in range(l)]
    FLAG = True
    
    def rec(arr):
        res = [[]]
        for i in range(len(arr)):
            for a in rec(arr[:i] + arr[i+1:]):
                res.append([arr[i]]+a)
        return res
    
    tup = {}
    
    for a in rec(numbers)[1:]:
        n = int(''.join(a))
        if not n in tup:
            answer += 1
            if n > 2:
                i = 2
                while n //i >= i:
                    if n % i == 0:
                        answer -= 1
                        break
                    i+=1
            elif n < 2:
                answer -= 1
                continue
            tup[n] = 1
            
    return answer