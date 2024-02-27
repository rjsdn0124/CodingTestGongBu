def solution(name):
    answer = 0
    
    for c in name:
        answer += min(ord(c) - ord('A'), ord('Z') - ord(c) + 1)
        
    n = len(name)
    minmove = n - 1
    i = 0
    while i < n:
        if name[i] == 'A':
            ti = i
            while i < n and name[i] == 'A':
                i += 1
            if ti == 0:
                minmove = n - i + ti
            else:
                from_last = n - i
                minmove = min(minmove, 2 * (ti - 1) + from_last, (ti - 1) + 2 * from_last)
        i+=1
    answer += minmove   
    return answer