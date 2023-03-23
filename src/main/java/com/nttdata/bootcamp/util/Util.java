package com.nttdata.bootcamp.util;

import com.nttdata.bootcamp.model.Account;
import com.nttdata.bootcamp.model.AccountDepositResponseDto;
import com.nttdata.bootcamp.model.AccountResponseDto;
import com.nttdata.bootcamp.model.AccountWithdrawResponseDto;
import com.nttdata.bootcamp.model.Company;
import com.nttdata.bootcamp.model.CompanyResponseDto;
import com.nttdata.bootcamp.model.Credit;
import com.nttdata.bootcamp.model.CreditCard;
import com.nttdata.bootcamp.model.CreditCardConsumeResponseDto;
import com.nttdata.bootcamp.model.CreditCardPayResponseDto;
import com.nttdata.bootcamp.model.CreditCardResponseDto;
import com.nttdata.bootcamp.model.CreditPayResponseDto;
import com.nttdata.bootcamp.model.CreditResponseDto;
import com.nttdata.bootcamp.model.MessageStatus;
import com.nttdata.bootcamp.model.Person;
import com.nttdata.bootcamp.model.PersonResponseDto;
import com.nttdata.bootcamp.model.Transaction;
import com.nttdata.bootcamp.model.TransactionResponseDto;

import java.util.List;

public class Util {

    public static PersonResponseDto personToResponse(List<Person> person){
        PersonResponseDto personResponseDto = new PersonResponseDto();
        MessageStatus msg = new MessageStatus();
        if (person.size()>0){
            msg.setStatusCode("0");
            msg.setMessage("Operación exitosa.");
            personResponseDto.setStatusDto(msg);
            personResponseDto.setPerson(person);
        }
        else{
            msg.setStatusCode("1");
            msg.setMessage("No existen clientes personales.");
            personResponseDto.setStatusDto(msg);
            personResponseDto.setPerson(person);
        }
        return personResponseDto;
    }

    public static CompanyResponseDto companyToResponse(List<Company> company){
        CompanyResponseDto companyResponseDto = new CompanyResponseDto();
        MessageStatus msg = new MessageStatus();
        if (company.size()>0){
            msg.setStatusCode("0");
            msg.setMessage("Operación exitosa.");
            companyResponseDto.setStatusDto(msg);
            companyResponseDto.setCompany(company);
        }
        else{
            msg.setStatusCode("1");
            msg.setMessage("No existen clientes empresariales.");
            companyResponseDto.setStatusDto(msg);
            companyResponseDto.setCompany(company);
        }
        return companyResponseDto;
    }

    public static TransactionResponseDto transactionToResponse(List<Transaction> transaction){
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        MessageStatus msg = new MessageStatus();
        if (transaction.size()>0){
            msg.setStatusCode("0");
            msg.setMessage("Operación exitosa.");
            transactionResponseDto.setStatusDto(msg);
            transactionResponseDto.setTransaction(transaction);
        }
        else{
            msg.setStatusCode("1");
            msg.setMessage("No existen transacciones.");
            transactionResponseDto.setStatusDto(msg);
            transactionResponseDto.setTransaction(transaction);
        }
        return transactionResponseDto;
    }

    public static CreditCardResponseDto creditCardToResponse(List<CreditCard> creditCard){
        CreditCardResponseDto creditCardResponseDto = new CreditCardResponseDto();
        MessageStatus msg = new MessageStatus();
        if (creditCard.size()>0){
            msg.setStatusCode("0");
            msg.setMessage("Operación exitosa.");
            creditCardResponseDto.setStatusDto(msg);
            creditCardResponseDto.setCreditCard(creditCard);
        }
        else{
            msg.setStatusCode("1");
            msg.setMessage("No existen tarjetas de credito.");
            creditCardResponseDto.setStatusDto(msg);
            creditCardResponseDto.setCreditCard(creditCard);
        }
        return creditCardResponseDto;
    }

    public static CreditResponseDto creditToResponse(List<Credit> credit){
        CreditResponseDto creditResponseDto = new CreditResponseDto();
        MessageStatus msg = new MessageStatus();
        if (credit.size()>0){
            msg.setStatusCode("0");
            msg.setMessage("Operación exitosa.");
            creditResponseDto.setStatusDto(msg);
            creditResponseDto.setCredit(credit);
        }
        else{
            msg.setStatusCode("1");
            msg.setMessage("No existen creditos.");
            creditResponseDto.setStatusDto(msg);
            creditResponseDto.setCredit(credit);
        }
        return creditResponseDto;
    }

    public static CreditPayResponseDto creditPayToResponse(List<Credit> credit){
        CreditPayResponseDto creditPayResponseDto = new CreditPayResponseDto();
        MessageStatus msg = new MessageStatus();
        if (credit.size()>0){
            msg.setStatusCode("0");
            msg.setMessage("Operación exitosa.");
            creditPayResponseDto.setStatusDto(msg);
        }
        else{
            msg.setStatusCode("1");
            msg.setMessage("No existen creditos.");
            creditPayResponseDto.setStatusDto(msg);
        }
        return creditPayResponseDto;
    }

    public static CreditCardPayResponseDto creditCardPayToResponse(List<CreditCard> creditCard){
        CreditCardPayResponseDto creditCardPayResponseDto = new CreditCardPayResponseDto();
        MessageStatus msg = new MessageStatus();
        if (creditCard.size()>0){
            msg.setStatusCode("0");
            msg.setMessage("Operación exitosa.");
            creditCardPayResponseDto.setStatusDto(msg);
        }
        else{
            msg.setStatusCode("1");
            msg.setMessage("No existen creditos.");
            creditCardPayResponseDto.setStatusDto(msg);
        }
        return creditCardPayResponseDto;
    }

    public static CreditCardConsumeResponseDto creditCardConsumeToResponse(List<CreditCard> creditCard){
        CreditCardConsumeResponseDto creditCardPayResponseDto = new CreditCardConsumeResponseDto();
        MessageStatus msg = new MessageStatus();
        if (creditCard.size()>0){
            msg.setStatusCode("0");
            msg.setMessage("Operación exitosa.");
            creditCardPayResponseDto.setStatusDto(msg);
        }
        else{
            msg.setStatusCode("1");
            msg.setMessage("No existen creditos.");
            creditCardPayResponseDto.setStatusDto(msg);
        }
        return creditCardPayResponseDto;
    }
    public static AccountResponseDto accountToResponse(List<Account> account){
        AccountResponseDto accountResponseDto = new AccountResponseDto();
        MessageStatus msg = new MessageStatus();
        msg.setStatusCode("1");
        msg.setMessage("No existen cuentas.");
        accountResponseDto.setAccount(null);
        if (account.size()>0 && !account.get(0).getId().equals("0")){
            msg.setStatusCode("0");
            msg.setMessage("Operación exitosa.");
            accountResponseDto.setAccount(account);
        }
        if(account.size()>0){
            switch (account.get(0).getStatus()) {
                case 3:
                    msg.setStatusCode("3");
                    msg.setMessage("Cliente ya tiene ese tipo de cuenta.");
                    break;
                case 4:
                    msg.setStatusCode("4");
                    msg.setMessage("No existe el cliente.");
                    break;
            }
        }
        accountResponseDto.setStatusDto(msg);
        return accountResponseDto;
    }

    public static AccountDepositResponseDto accountDepositToResponse(List<Account> account){
        AccountDepositResponseDto accountDepositResponseDto = new AccountDepositResponseDto();
        MessageStatus msg = new MessageStatus();
        msg.setStatusCode("1");
        msg.setMessage("No existen cuentas.");
        if (account.size()>0){
            msg.setStatusCode("0");
            msg.setMessage("Operación exitosa.");
        }
        accountDepositResponseDto.setStatusDto(msg);
        return accountDepositResponseDto;
    }

    public static AccountWithdrawResponseDto accountWithdrawToResponse(List<Account> account){
        AccountWithdrawResponseDto accountWithdrawResponseDto = new AccountWithdrawResponseDto();
        MessageStatus msg = new MessageStatus();
        msg.setStatusCode("1");
        msg.setMessage("No existen cuentas.");
        if (account.size()>0){
            msg.setStatusCode("0");
            msg.setMessage("Operación exitosa.");
        }
        accountWithdrawResponseDto.setStatusDto(msg);
        return accountWithdrawResponseDto;
    }

}
