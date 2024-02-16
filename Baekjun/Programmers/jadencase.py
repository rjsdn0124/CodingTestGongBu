def solution(s):
    s=s.lower()
    answer = ''
    prev=' '
    for c in s:
        if prev == ' ' and 'a'<= c <= 'z':
            c = c.upper()
        answer += c 
        prev = c
    return answer