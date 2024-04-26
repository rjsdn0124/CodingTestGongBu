def solution(people, limit):
    answer = 0
    people.sort(reverse = True)
    r = len(people)-1
    i = 0
    while i <= r:
        temp = limit - people[i] - people[r]
        if temp >= 0:
            r -= 1
        answer += 1
        i+=1
    return answer