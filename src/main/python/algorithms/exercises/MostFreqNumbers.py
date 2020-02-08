from typing import List
from collections import Counter
import heapq


class Solution:

    def topKFrequent(self, nums: List[int], k: int) -> List[int]:

        heap = []

        counter = Counter(nums)
        for num, count in counter.items():
            heapq.heappush(heap, (count, num))
            if len(heap) > k:
                heapq.heappop(heap)

        return [num for _, num in heap]


result = Solution().topKFrequent([1, 1, 6, 1, 3, 6], 2)
print(result)
