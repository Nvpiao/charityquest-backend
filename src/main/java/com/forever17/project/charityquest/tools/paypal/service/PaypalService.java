package com.forever17.project.charityquest.tools.paypal.service;

import com.forever17.project.charityquest.constants.CharityConstants;
import com.forever17.project.charityquest.enums.DonationType;
import com.forever17.project.charityquest.tools.paypal.config.PaypalConfig;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service of Paypal
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 23 Mar 2020
 * @since 1.0
 */
@Service
public class PaypalService {

    /**
     * paypal api context
     */
    private final APIContext apiContext;

    /**
     * configuration of paypal
     */
    private final PaypalConfig paypalConfig;

    @Autowired
    public PaypalService(APIContext apiContext, PaypalConfig paypalConfig) {
        this.apiContext = apiContext;
        this.paypalConfig = paypalConfig;
    }

    /**
     * create a payment
     *
     * @param donationType  type of donation
     * @param money         total money to pay
     * @param fundraisingId id of fundraising
     * @param publicId      id of public
     * @param url           url of fundraising
     * @return instance of Payment
     * @throws PayPalRESTException exception from paypal
     */
    public Payment createPayment(DonationType donationType, String fundraisingId, String charityId,
                                 String publicId, float money, String url) throws PayPalRESTException {
        // amount
        Amount amount = new Amount();
        amount.setCurrency(CharityConstants.PAYPAL_CURRENCY);
        amount.setTotal(String.format("%.2f", money));

        // transaction
        Transaction transaction = new Transaction();
        transaction.setDescription(CharityConstants.PAYPAL_PAY_DESCRIPTION);
        transaction.setAmount(amount);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(CharityConstants.PAYPAL_METHOD_PAYPAL);

        // payment
        Payment payment = new Payment();
        payment.setIntent(CharityConstants.PAYPAL_INTENT_SALE);
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(paypalConfig.getCancelUrl());

        // common url
        String commonUrl = CharityConstants.PAYPAL_SUCCESS_LINK_PUBLIC_ID + publicId +
                CharityConstants.PAYPAL_SUCCESS_LINK_MONEY + money;
        String successUrl = null;
        if (donationType == DonationType.DONATION) {
            successUrl = paypalConfig.getSuccessDonationUrl() + '/'
                    + CharityConstants.PAYPAL_SUCCESS_LINK_FLAG_DONATION + commonUrl
                    + CharityConstants.PAYPAL_SUCCESS_LINK_CHARITY_ID + charityId;
        } else if (donationType == DonationType.FUNDRAISING) {
            successUrl = paypalConfig.getSuccessFundraisingUrl() + '/' + url
                    + CharityConstants.PAYPAL_SUCCESS_LINK_FLAG_FUNDRAISING + commonUrl
                    + CharityConstants.PAYPAL_SUCCESS_LINK_FUNDRAISING_ID + fundraisingId;
        }
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        // create a payment
        return payment.create(apiContext);
    }

    /**
     * execute a payment
     *
     * @param paymentId id of payment
     * @param payerId   id of payer
     * @return instance of Payment
     * @throws PayPalRESTException exception from paypal
     */
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }
}
