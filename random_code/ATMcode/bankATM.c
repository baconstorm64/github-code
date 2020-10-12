#include <stdio.h>

//Proper input = 1000000000000000000

int main(){
	int user_input;
	int savings_amount;
	int checking_amount;	
	int tmp;

	printf("How much money is in your savings account?\n");
	
	printf("> ");
	scanf("%d", &savings_amount);

	printf("How much money is in your checking account?\n");
	
	printf("> ");
	scanf("%d", &checking_amount);
		
	while(1){
		printf("Welcome to your ATM.\n");
		printf("Please type the following:\n");	

		printf("1) View amount of money in your account\n");
		printf("2) Withdraw money from your account\n");
		printf("3) Exit ATM\n");
		
		printf("> ");
		scanf("%d", &user_input);

		if(user_input == 1){
			printf("Which account would you like to view?\n");
			printf("1) Savings account\n");
			printf("2) Checking account\n");

			printf("> ");
			scanf("%d", &user_input);
			if(user_input == 1){
				printf("You have $%d in your savings account\n", savings_amount);
			}
			else{
				printf("You have $%d in your checking accounts\n", checking_amount);
			}
		}

		if(user_input == 2){
			printf("Which account would you lke to withdraw from?\n");
			printf("1) Savings account\n");
			printf("2) Checking account\n");
			
			printf("> ");
			scanf("%d", &user_input);

			if(user_input == 1){
				while(1){

					printf("Your current balance is $%d. How much would you like to withdraw?\n", savings_amount);
					
					printf("> ");
					scanf("%d", &user_input);

				
					tmp = savings_amount - user_input;
				
					if(tmp < 0){
						printf("If you take out that much, you will be in the negative. Enter a number less than or equal to your current balance.\n");
					}
					if(tmp > 0){
						savings_amount = tmp;
						printf("Your current balance is $%d\n", savings_amount);
						break;
					}	
				}	
			}
			
			if(user_input == 2){
				printf("Your current balance is $%d. How much would you like to withdraw?\n", checking_amount);
				
				printf("> ");
				scanf("%d", &user_input);

				tmp = checking_amount - user_input;

				if(tmp < 0){
					printf("If you take out that much, you will be in the negative. Enter a number less than or equal to your current balance.\n");
				}
				if(tmp > 0){
					checking_amount = tmp;
					printf("Your current balance is $%d\n", checking_amount);
					continue;
				}
			}	
		}

		if(user_input == 3){
			break;
		}
	
	}
		
	return 0;
}
