class Solution:

    def reconstruct_queue(self, people):
        people.sort(key=lambda x: (-x[0], x[1]))
        result = []
        for p in people:
            result.insert(p[1], p)
        return result


print(Solution().reconstruct_queue(
    [[7, 0], [4, 4], [7, 1], [5, 0], [6, 1], [5, 2]]))
# [[5, 0], [7, 0], [5, 2], [6, 1], [4, 4], [7, 1]]
