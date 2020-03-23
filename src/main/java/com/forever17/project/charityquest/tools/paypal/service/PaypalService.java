package com.forever17.project.charityquest.tools.paypal.service;

import com.forever17.project.charityquest.constants.CharityConstants;
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
     * @param total         total money to pay
     * @param fundraisingId id of fundraising
     * @param publicId      id of public
     * @return instance of Payment
     * @throws PayPalRESTException exception from paypal
     */
    public Payment createPayment(float total, String fundraisingId, String publicId) throws PayPalRESTException {
        // amount
        Amount amount = new Amount();
        amount.setCurrency(CharityConstants.PAYPAL_CURRENCY);
        amount.setTotal(String.format("%.2f", total));

        // tanscation
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
        String successUrl = paypalConfig.getSuccessUrl() +
                CharityConstants.PAYPAL_SUCCESS_LINK_FLAG +
                CharityConstants.PAYPAL_SUCCESS_LINK_FUNDRAISING_ID + fundraisingId +
                CharityConstants.PAYPAL_SUCCESS_LINK_PUBLIC_ID + publicId +
                CharityConstants.PAYPAL_SUCCESS_LINK_MONEY + total;
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
