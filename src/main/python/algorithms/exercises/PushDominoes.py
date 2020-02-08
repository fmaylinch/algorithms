class Solution:
    
    def pushDominoes(self, dominoes: str) -> str:

        n = len(dominoes)
        force = [0] * n
        f = 0

        # LTR
        for i in range(n):
            if dominoes[i] == "R":
                f = n
            elif dominoes[i] == "L":
                f = 0
            else:
                f = max(f-1, 0)
            force[i] = f

        # RTL
        for i in reverse_range(n):
            if dominoes[i] == "L":
                f = n
            elif dominoes[i] == "R":
                f = 0
            else:
                f = max(f-1, 0)
            force[i] -= f

        result = []

        # Result
        for i in range(n):
            if force[i] > 0:
                result.append('R')
            elif force[i] < 0:
                result.append('L')
            else:
                result.append('.')

        return "".join(result)


def reverse_range(n):
    return range(n-1, -1, -1)


print(Solution().pushDominoes(".L.R...LR..L.."))
