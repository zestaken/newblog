package linkedList;

import org.junit.jupiter.api.Test;

public class PalindromeLinkedListTest {

    @Test
    public void test() {
        PalindromeLinkedList234 palindromeLinkedList = new PalindromeLinkedList234();

        int[] input1 = {1, 2, 2, 1};
        ListNode head1 = ListNode.makeLinkedList(input1);
        boolean expect1 = true;

        int[] input2 = {1, 2};
        ListNode head2 = ListNode.makeLinkedList(input2);
        boolean expect2 = false;

        int[] input3 = {1, 0, 1};
        ListNode head3 = ListNode.makeLinkedList(input3);
        boolean expect3 = true;
        
        boolean res1 = palindromeLinkedList.isPalindrome(head1);
        assert res1 == expect1 : res1;

        boolean res2 = palindromeLinkedList.isPalindrome(head2);
        assert res2 == expect2 : res2;

        boolean res3 = palindromeLinkedList.isPalindrome(head3);
        assert res3 == expect3 : res3;
    }
}
