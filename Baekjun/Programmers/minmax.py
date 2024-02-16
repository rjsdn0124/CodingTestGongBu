def solution(s):
    a = list(map(int, s.split(' ')))
    answer = f'{min(a)} {max(a)}'
    return answer