def solution(n, words):
    prev = words[0][0]
    for i,word in enumerate(words):
        if word in words[:i] or not word[0] == prev:
            return [i % n + 1,i // n + 1]
        prev = word[-1]
    return [0,0]