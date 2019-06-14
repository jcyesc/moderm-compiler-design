	.globl main
main:
	addi $t1,$sp,0
	addi $sp,$sp,-4000
	addi $t2,$sp,0
	addi $t2,$t2,-4000
	la $t3, HEAPPTR
	sw $t2,0($t3)
	sw $ra,4($sp)
	jal main1
	li $v0, 10
	syscall
getCircle1:
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $ra, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 12
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $v0, 4($t1)
	addi $t1, $t1, 4
	j getCircleEnd1
getCircleEnd1:
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $ra, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	jr $ra
setCircle1:
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $ra, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 12
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
setCircleEnd1:
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $ra, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	jr $ra
Circle1:
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $ra, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 12
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $v0, 4($t1)
	addi $t1, $t1, 4
	j CircleEnd1
CircleEnd1:
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $ra, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	jr $ra
setRadio1:
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $ra, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 12
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, -4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
setRadioEnd1:
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $ra, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	jr $ra
getRadio1:
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $ra, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 12
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, -4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $v0, 4($t1)
	addi $t1, $t1, 4
	j getRadioEnd1
getRadioEnd1:
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $ra, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	jr $ra
setCenter1:
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $ra, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 12
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
setCenterEnd1:
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $ra, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	jr $ra
getCenter1:
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $ra, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 12
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $v0, 4($t1)
	addi $t1, $t1, 4
	j getCenterEnd1
getCenterEnd1:
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $ra, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	jr $ra
Point1:
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $ra, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 12
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, -4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $v0, 4($t1)
	addi $t1, $t1, 4
	j PointEnd1
PointEnd1:
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $ra, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	jr $ra
print1:
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $ra, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 12
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal Print
	addi $sp, $sp, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getY1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal Print
	addi $sp, $sp, 4
	addi $sp, $sp, 0
	addi $t1, $t1, 0
	jal Println
	addi $sp, $sp, 0
printEnd1:
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $ra, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	jr $ra
foo1:
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $ra, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 12
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal Print
	addi $sp, $sp, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getY1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal Print
	addi $sp, $sp, 4
	addi $sp, $sp, 0
	addi $t1, $t1, 0
	jal Println
	addi $sp, $sp, 0
fooEnd1:
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $ra, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	jr $ra
getY1:
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $ra, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 12
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, -4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $v0, 4($t1)
	addi $t1, $t1, 4
	j getYEnd1
getYEnd1:
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $ra, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	jr $ra
setY1:
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $ra, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 12
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, -4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
setYEnd1:
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $ra, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	jr $ra
getX1:
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $ra, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 12
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $v0, 4($t1)
	addi $t1, $t1, 4
	j getXEnd1
getXEnd1:
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $ra, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	jr $ra
setX1:
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $ra, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 12
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
setXEnd1:
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $ra, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	jr $ra
main1:
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 48
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $ra, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 52
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 56
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	sw $sp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 60
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	addi $t2, $zero, 23
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	addi $t2, $zero, 46
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	addi $t2, $zero, 2
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	mult $t2, $t3
	mflo $t2
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal allocate
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	lw $t2, 8($t1)
	sw $t2, -4($sp)
	lw $t2, 12($t1)
	sw $t2, -8($sp)
	addi $sp, $sp, -12
	addi $t1, $t1, 12
	jal Point1
	addi $sp, $sp, 12
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	lw $t2, 8($t1)
	sw $t2, -4($sp)
	addi $sp, $sp, -8
	addi $t1, $t1, 8
	jal setX1
	addi $sp, $sp, 8
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	lw $t2, 8($t1)
	sw $t2, -4($sp)
	addi $sp, $sp, -8
	addi $t1, $t1, 8
	jal setY1
	addi $sp, $sp, 8
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal Print
	addi $sp, $sp, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getY1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal Print
	addi $sp, $sp, 4
	addi $sp, $sp, 0
	addi $t1, $t1, 0
	jal Println
	addi $sp, $sp, 0
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal print1
	addi $sp, $sp, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -12
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	addi $t2, $zero, 2
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	mult $t2, $t3
	mflo $t2
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal allocate
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal Circle1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -12
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -8
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	lw $t2, 8($t1)
	sw $t2, -4($sp)
	addi $sp, $sp, -8
	addi $t1, $t1, 8
	jal setCenter1
	addi $sp, $sp, 8
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -12
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCenter1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal Print
	addi $sp, $sp, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -12
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCenter1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getY1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal Print
	addi $sp, $sp, 4
	addi $sp, $sp, 0
	addi $t1, $t1, 0
	jal Println
	addi $sp, $sp, 0
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -16
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	addi $t2, $zero, 1
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	mult $t2, $t3
	mflo $t2
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal allocate
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -16
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -12
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	lw $t2, 8($t1)
	sw $t2, -4($sp)
	addi $sp, $sp, -8
	addi $t1, $t1, 8
	jal setCircle1
	addi $sp, $sp, 8
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -16
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCircle1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCenter1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal Print
	addi $sp, $sp, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -16
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCircle1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCenter1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getY1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal Print
	addi $sp, $sp, 4
	addi $sp, $sp, 0
	addi $t1, $t1, 0
	jal Println
	addi $sp, $sp, 0
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -16
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCircle1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCenter1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal Print
	addi $sp, $sp, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -16
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCircle1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCenter1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getY1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal Print
	addi $sp, $sp, 4
	addi $sp, $sp, 0
	addi $t1, $t1, 0
	jal Println
	addi $sp, $sp, 0
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -20
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	addi $t2, $zero, 5
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -24
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -20
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	mult $t2, $t3
	mflo $t2
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal allocate
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -28
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	j forTest1
forStart1:
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -24
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -28
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	mult $t2, $t3
	mflo $t2
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	addi $t2, $zero, 1
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	mult $t2, $t3
	mflo $t2
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal allocate
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -24
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -28
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	mult $t2, $t3
	mflo $t2
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 2
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	mult $t2, $t3
	mflo $t2
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal allocate
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal Circle1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	lw $t2, 8($t1)
	sw $t2, -4($sp)
	addi $sp, $sp, -8
	addi $t1, $t1, 8
	jal setCircle1
	addi $sp, $sp, 8
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -24
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -28
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	mult $t2, $t3
	mflo $t2
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCircle1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 2
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	mult $t2, $t3
	mflo $t2
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal allocate
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -28
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -28
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 1
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	lw $t2, 8($t1)
	sw $t2, -4($sp)
	lw $t2, 12($t1)
	sw $t2, -8($sp)
	addi $sp, $sp, -12
	addi $t1, $t1, 12
	jal Point1
	addi $sp, $sp, 12
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	lw $t2, 8($t1)
	sw $t2, -4($sp)
	addi $sp, $sp, -8
	addi $t1, $t1, 8
	jal setCenter1
	addi $sp, $sp, 8
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -24
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -28
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	mult $t2, $t3
	mflo $t2
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCircle1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCenter1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal print1
	addi $sp, $sp, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -32
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -24
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -28
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	mult $t2, $t3
	mflo $t2
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCircle1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCenter1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -32
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal print1
	addi $sp, $sp, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -24
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -28
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	mult $t2, $t3
	mflo $t2
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCircle1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCenter1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -24
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -28
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	mult $t2, $t3
	mflo $t2
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCircle1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCenter1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getX1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 10
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	lw $t2, 8($t1)
	sw $t2, -4($sp)
	addi $sp, $sp, -8
	addi $t1, $t1, 8
	jal setX1
	addi $sp, $sp, 8
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -36
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -24
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -28
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	mult $t2, $t3
	mflo $t2
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCircle1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCenter1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getX1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -36
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal Print
	addi $sp, $sp, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -24
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -28
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	mult $t2, $t3
	mflo $t2
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCircle1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCenter1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal Print
	addi $sp, $sp, 4
	addi $sp, $sp, 0
	addi $t1, $t1, 0
	jal Println
	addi $sp, $sp, 0
	addi $sp, $sp, 0
	addi $t1, $t1, 0
	jal Println
	addi $sp, $sp, 0
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -28
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -28
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 1
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
forTest1:
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -28
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -20
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	slt $t2, $t2 $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	addi $t1, $t1, 4
	bgtz $t2, forStart1
forEnd1:
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -40
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	addi $t2, $zero, 0
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	j forTest2
forStart2:
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -44
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -24
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 4
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -40
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	mult $t2, $t3
	mflo $t2
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -44
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCircle1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal getCenter1
	addi $sp, $sp, 4
	sw $v0, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 4($t1)
	sw $t2, 0($sp)
	addi $sp, $sp, -4
	addi $t1, $t1, 4
	jal foo1
	addi $sp, $sp, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -40
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -40
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	addi $t2, $zero, 1
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sw $t3, 0($t2)
	addi $t1, $t1, 8
forTest2:
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -40
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, -20
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	add $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	slt $t2, $t2 $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	addi $t1, $t1, 4
	bgtz $t2, forStart2
forEnd2:
mainEnd1:
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 48
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $ra, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 56
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $sp, 4($t1)
	addi $t1, $t1, 4
	sw $fp, 0($t1)
	addi $t1, $t1, -4
	addi $t2, $zero, 52
	sw $t2, 0($t1)
	addi $t1, $t1, -4
	lw $t2, 8($t1)
	lw $t3, 4($t1)
	sub $t2, $t2, $t3
	sw $t2, 8($t1)
	addi $t1, $t1, 4
	lw $t2, 4($t1)
	lw $t2, 0($t2)
	sw $t2, 4($t1)
	lw $fp, 4($t1)
	addi $t1, $t1, 4
	jr $ra
Print:
	lw $a0, 4($sp)
	li $v0, 1
	syscall
	li $v0,4
	la $a0, sp
	syscall
	jr $ra
Println:
	li $v0,4
	la $a0, cr
	syscall
	jr $ra
Read:
	li $v0,5
	syscall
	jr $ra
allocate:
	la $t2, HEAPPTR
	lw $v0,0($t2)
	lw $t3, 4($sp)
	sub $t3,$v0,$t3
	sw $t3,0($t2)
	jr $ra
	.data
cr:
	.asciiz "\n"
sp:
	.asciiz " "
HEAPPTR:
	.word 0
