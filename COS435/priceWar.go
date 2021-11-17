package main
import "fmt"

func main(){

	//Added to allow pausing in between function calls
	var continueString string
	PeacePeace(3.20, 3.20, 5, 5)
	fmt.Scanln(&continueString)
	PeaceWar(3.20, 3.20, 5, 5, "B", "A")
	fmt.Scanln(&continueString)
	PeaceWar(3.20, 3.20, 5, 5, "A", "B")
	fmt.Scanln(&continueString)
	WarWar(3.20, 3.20, 5, 5)
}


func PeacePeace(priceA float32, priceB float32, customersA float32, customersB float32){
	companyAPrice := priceA
	companyBPrice := priceB
	companyACustomers := customersA
	companyBCustomers := customersB
	companyAProfit := float32(0)
	companyBProfit := float32(0)

	fmt.Printf("Company A AND Company B are peaceful\n")
	fmt.Printf("-------------------------------------\n")

	/*
	If gas company B lowers the price to lower than gas company A's,
	customers will go to gas company B, so gas company A needs to lower it more
	so that more customers will go to gas company A
	*/
	for i:=0; i < 5; i++{
		fmt.Printf("Day %d\n", i+1)
		fmt.Printf("----------------------------\n")
		if(companyAPrice < companyBPrice){
			companyBPrice = companyAPrice - 0.1
		}
		if(companyBPrice < companyAPrice){
			companyAPrice = companyBPrice - 0.1
		}

		if(companyAPrice < companyBPrice){
			companyACustomers += 1
			companyBCustomers -= 1
		}
		if(companyBPrice < companyAPrice){
			companyBCustomers += 1
			companyACustomers -= 1
		}
		companyAProfit += companyAPrice * companyACustomers
		companyBProfit += companyBPrice * companyBCustomers
		fmt.Printf("Company A gas price: $%.2f\n", companyAPrice)
		fmt.Printf("Company B gas price: $%.2f\n", companyBPrice)
		fmt.Printf("Company A customers: %.0f\n", companyACustomers)
		fmt.Printf("Company B customers: %.0f\n", companyBCustomers)
		fmt.Printf("Company A profit: $%.2f\n", companyAProfit)
		fmt.Printf("Company B profit: $%.2f\n", companyBProfit)
		fmt.Printf("----------------------------\n")
	}

	fmt.Printf("-------------------------------------\n")
	fmt.Printf("Final Company A gas price: $%.2f\n", companyAPrice)
	fmt.Printf("Final Company B gas price: $%.2f\n", companyBPrice)
	fmt.Printf("Final Company A customers: %.0fd\n", companyACustomers)
	fmt.Printf("Final Company B customers: %.0f\n", companyBCustomers)
	fmt.Printf("Final Company A profit: $%.2f\n", companyAProfit)
	fmt.Printf("Final Company B profit: $%.2f\n", companyBProfit)
	fmt.Printf("-------------------------------------\n")
	/*
	However, because this is a peace-peace agreement,
	neither company lowers the price, and therefore 
	both make the most money possible
	*/

}


func PeaceWar(priceA float32, priceB float32, customersA float32, customersB float32, agressor string, peaceful string){

	companyAPrice := priceA
	companyBPrice := priceB
	companyACustomers := customersA
	companyBCustomers := customersB
	companyAProfit := float32(0)
	companyBProfit := float32(0)


	/*
	Depending on which company decides to do nothing
	and which company decides to continue lowering prices,
	one company will gain more customers while the other loses them,
	so every day the agressive company will make more money 
	even though they continually lower their gas price.
	*/
	fmt.Printf("\n\n")
	fmt.Printf("Company %s is peaceful, Company %s lowers prices\n", peaceful, agressor)
	fmt.Printf("-------------------------------------\n")


	for i:=0; i < 5; i++{
		fmt.Printf("Day %d\n", i+1)
		fmt.Printf("----------------------------\n")
		if(peaceful == "A"){
			if(companyBPrice > 3.10){
				companyBPrice -= 0.1
			}else{
				companyBPrice -= 0.01
			}

		}else{
			if(companyAPrice > 3.10){
				companyAPrice -= 0.1
			}else{
				companyAPrice -= 0.01
			}
		}
		if(companyBPrice < companyAPrice){
			companyACustomers -= 1
			companyBCustomers += 1
		}
		if(companyAPrice < companyBPrice){
			companyACustomers += 1
			companyBCustomers -= 1
		}
		companyAProfit += companyAPrice * companyACustomers
		companyBProfit += companyBPrice * companyBCustomers
		fmt.Printf("Company A gas price: $%.2f\n", companyAPrice)
		fmt.Printf("Company B gas price: $%.2f\n", companyBPrice)
		fmt.Printf("Company A customers: %.0f\n", companyACustomers)
		fmt.Printf("Company B customers: %.0f\n", companyBCustomers)
		fmt.Printf("Company A profit: $%.2f\n", companyAProfit)
		fmt.Printf("Company B profit: $%.2f\n", companyBProfit)
		fmt.Printf("----------------------------\n")
	}
	fmt.Printf("-------------------------------------\n")
	fmt.Printf("Final Company A gas price: $%.2f\n", companyAPrice)
	fmt.Printf("Final Company B gas price: $%.2f\n", companyBPrice)
	fmt.Printf("Final Company A customers: %.0f\n", companyACustomers)
	fmt.Printf("Final Company B customers: %.0f\n", companyBCustomers)
	fmt.Printf("Final Company A profit: $%.2f\n", companyAProfit)
	fmt.Printf("Final Company B profit: $%.2f\n", companyBProfit)
	fmt.Printf("-------------------------------------\n")


}



func WarWar(priceA float32, priceB float32, customersA float32, customersB float32){

	companyAPrice := priceA
	companyBPrice := priceB
	companyACustomers := customersA
	companyBCustomers := customersB
	companyAProfit := float32(0)
	companyBProfit := float32(0)


	/*
	If both companies keep lowering their gas prices
	they will both still make the same amount of money.
	However, the amount of money is less than if they were to
	just keep the gas prices the same.
	*/

	fmt.Printf("\n\n")
	fmt.Printf("Both companies continue to lower gas prices\n")
	fmt.Printf("-------------------------------------\n")


	for i:=0; i < 5; i++{
		fmt.Printf("Day %d\n", i+1)
		fmt.Printf("----------------------------\n")
		if(companyAPrice > 3.10){
			companyAPrice -= 0.1
		}else{
			companyAPrice -= 0.01
		}

		if(companyBPrice > 3.10){
			companyBPrice -= 0.1
		}else{
			companyBPrice -= 0.01
		}
		if(companyBPrice < companyAPrice){
			companyACustomers -= 1
			companyBCustomers += 1
		}
		if(companyAPrice < companyBPrice){
			companyACustomers += 1
			companyBCustomers -= 1
		}
		companyAProfit += companyAPrice * companyACustomers
		companyBProfit += companyBPrice * companyBCustomers
		fmt.Printf("Company A gas price: $%.2f\n", companyAPrice)
		fmt.Printf("Company B gas price: $%.2f\n", companyBPrice)
		fmt.Printf("Company A customers: %.0f\n", companyACustomers)
		fmt.Printf("Company B customers: %.0f\n", companyBCustomers)
		fmt.Printf("Company A profit: $%.2f\n", companyAProfit)
		fmt.Printf("Company B profit: $%.2f\n", companyBProfit)
		fmt.Printf("----------------------------\n")
	}
	fmt.Printf("-------------------------------------\n")
	fmt.Printf("Final Company A gas price: $%.2f\n", companyAPrice)
	fmt.Printf("Final Company B gas price: $%.2f\n", companyBPrice)
	fmt.Printf("Final Company A customers: %.0f\n", companyACustomers)
	fmt.Printf("Final Company B customers: %.0f\n", companyBCustomers)
	fmt.Printf("Final Company A profit: $%.2f\n", companyAProfit)
	fmt.Printf("Final Company B profit: $%.2f\n", companyBProfit)
	fmt.Printf("-------------------------------------\n")


}
