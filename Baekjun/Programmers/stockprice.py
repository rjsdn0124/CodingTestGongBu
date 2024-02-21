def solution(prices):
    l = len(prices)
    answer = [0 for _ in range(l)]
    stack = []
    for i in range(l):
        if len(stack) != 0:
            while len(stack) > 0 and prices[stack[-1]] > prices[i]:
                answer[stack[-1]] = i - stack[-1]
                stack = stack[:-1]
        stack.append(i)
    for i in range(len(stack)):
        answer[stack[i]] = l - 1 - stack[i]
    return answer