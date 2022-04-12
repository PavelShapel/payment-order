package com.pavelshapel.aws.lambda.service.corporation.model.typed;

public enum CompanyType {
	OTHER(22, "Другая"),

	OWNER(22, "Собственные реквизиты"),

	STATE(13, "Государственное учереждение"),

	BANK(22, "Банк"),

	LANDLORD_TODAY(22, "Арендодатель (оплата по курсу на дату платежа)"),

	LANDLORD_FIRST_DAY(22, "Арендодатель (оплата по курсу на 1-ое число)"),

	LANDLORD_LAST_DAY(22, "Арендодатель (оплата по курсу на последний день предыдущего месяца)");

	private Integer paymentQueue;
	private String name;

	private CompanyType(final Integer paymentQueue, final String name) {
		this.paymentQueue = paymentQueue;
		this.name = name;
	}

	public Integer getPaymentQueue() {
		return paymentQueue;
	}

	public String getName() {
		return name;
	}
}