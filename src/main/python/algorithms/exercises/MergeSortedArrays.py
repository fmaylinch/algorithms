class Solution(object):

    def merge(self, nums1, n1, nums2, n2):

        i1 = n1 - 1
        i2 = n2 - 1
        i = n1 + n2 - 1

        while i >= 0:

            if i1 < 0 or (i2 >= 0 and nums1[i1] <= nums2[i2]):
                nums1[i] = nums2[i2]
                i2 -= 1
            else:
                nums1[i] = nums1[i1]
                i1 -= 1

            i -= 1


nums1 = [1, 2, 3, 0, 0, 0]
n1 = 3
num2 = [2, 5, 6]
n2 = 3

Solution().merge(nums1, n1, num2, n2)

print(nums1)





