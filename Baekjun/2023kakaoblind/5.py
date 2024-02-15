def solution(commands):
    answer = []
    grid = [["" for _ in range(51)] for _ in range(51)]
    mgrid = [[[i,j]for j in range(51)] for i in range(51)]
    for command in commands:
        carr= command.split()
        if carr[0] == "UPDATE":
            if len(carr) == 3:
                for i in range(51):
                    for j in range(51):
                        if grid[i][j] == carr[1]:
                            grid[i][j] = carr[2]
            else:
                r = int(carr[1])
                c = int(carr[2])
                mr,mc = mgrid[r][c]
                for i in range(51):
                    for j in range(51):
                        if mgrid[i][j] == [mr,mc]:
                            grid[i][j] = carr[3]
        elif carr[0] == "MERGE":
            r1 = int(carr[1])
            c1 = int(carr[2])
            r2 = int(carr[3])
            c2 = int(carr[4])
            if grid[r1][c1] == "":
                r1,r2 = r2,r1
                c1,c2 = c2,c1
            mr1,mc1 = mgrid[r1][c1]
            mr2,mc2 = mgrid[r2][c2]
            value = grid[r1][c1]
            for i in range(51):
                for j in range(51):
                    if mgrid[i][j] == [mr2,mc2]:
                        mgrid[i][j] = [mr1,mc1]
                        grid[i][j] = value

        elif carr[0] == "UNMERGE":
            r = int(carr[1])
            c = int(carr[2])
            mr,mc = mgrid[r][c]
            value = grid[r][c]
            for i in range(51):
                for j in range(51):
                    if mgrid[i][j] == [mr,mc]:
                        mgrid[i][j] = [i,j]
                        grid[i][j] = ""
            grid[r][c] = value

        elif carr[0] == "PRINT":
            r = int(carr[1])
            c = int(carr[2])
            a,b = mgrid[r][c]
            answer.append(grid[a][b] if grid[a][b] != "" else "EMPTY")   
    for i in range(6):
        print(grid[i][:6])
    return answer