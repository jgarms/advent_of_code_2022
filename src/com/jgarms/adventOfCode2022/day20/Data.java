package com.jgarms.adventOfCode2022.day20;

import java.util.Scanner;

public class Data {
    Node[] originalNodes;
    Node head;
    Node zeroNode;
    final int size;

    public Data(Scanner scanner) {
        head = new Node(scanner.nextInt());
        Node previous = head;
        int numNodes = 1;
        while (scanner.hasNext()) {
            int value = scanner.nextInt();
            Node node = new Node(value);
            if (value == 0) {
                zeroNode = node;
            }
            node.left = previous;
            previous.right = node;
            previous = node;
            numNodes++;
        }
        // Hook up the head and the tail
        head.left = previous;
        previous.right = head;

        size = numNodes;
        originalNodes = new Node[size];
        Node current = head;
        for (int i=0; i<numNodes; i++) {
            originalNodes[i] = current;
            current = current.right;
        }
    }

    void mix(Node node) {
        if (node.value == 0) {
            return;
        }
        int distance = node.value;

        // Remove the node first
        if (node == head) {
            head = node.right;
        }
        Node left = node.left;
        Node right = node.right;
        left.right = right;
        right.left = left;

        if (distance < 0) {
            for (int i=0; i>distance; i--) {
                right = left;
                left = left.left;
            }
        } else {
            for (int i=0; i<distance; i++) {
                left = right;
                right = right.right;
            }
        }

        // insert at the new position
        left.right = node;
        node.left = left;
        node.right = right;
        right.left = node;
    }

    public void mix() {
        for (Node node:originalNodes) {
            mix(node);
        }
    }

    public int getGroveCoordinates() {
        int sum = 0;
        Node current = zeroNode;
        for (int i=0; i<3; i++) {
            for (int j=0; j<1000; j++) {
                current = current.right;
            }
            sum += current.value;
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(head.value);
        Node current = head.right;
        while(current != head) {
            sb.append(", ");
            sb.append(current.value);
            current = current.right;
        }
        return sb.toString();
    }
}
