def solution(k, ranges):
    answer = []
    dp = [0]
    maxx = 0
    while k > 1:
        maxx += 1
        prev = k
        if k % 2 == 0:
            k //= 2
        else:
            k = k * 3 + 1
        dp.append((prev + k) / 2 + dp[-1])
    for [s,e] in ranges:
        e += maxx
        if s > e:
            answer.append(-1)
        else:
            answer.append(dp[e] - dp[s])
    return answer