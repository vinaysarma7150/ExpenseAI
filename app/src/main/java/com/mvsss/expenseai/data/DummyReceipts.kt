package com.mvsss.expenseai.data

import com.mvsss.expenseai.data.model.LineItem
import com.mvsss.expenseai.data.model.ProcessingStep
import com.mvsss.expenseai.data.model.Receipt
import com.mvsss.expenseai.data.model.ReceiptCategory
import com.mvsss.expenseai.data.model.SpendingSummary
import com.mvsss.expenseai.data.model.StepStatus

object DummyReceipts {
    val spendingSummary = SpendingSummary(
        periodLabel = "September Spending",
        totalAmount = "₹3,780.00",
        receiptCount = 3,
        monthContext = "This month"
    )

    val historyTotal = "₹5,659"

    val receipts = listOf(
        Receipt(
            id = "dmart",
            merchant = "DMart Groceries",
            location = "HSR Layout, Sector 2",
            dateLabel = "Sep 29, 2026",
            timeLabel = "04:15 PM",
            category = ReceiptCategory.Groceries,
            amount = "₹1,240.00",
            confidencePercent = 98,
            itemsCount = 5,
            savingsLabel = "₹50 Saved",
            subtotal = "₹1,150.00",
            discount = "-₹50.00",
            taxLabel = "GST (12% Central & State)",
            taxAmount = "₹140.00",
            paymentMethod = "UPI (Google Pay)",
            receiptNumber = "INV-2026-98124",
            lineItems = listOf(
                LineItem("Milk 1L", "2 × ₹55.00", "₹55.00", "₹110.00"),
                LineItem("Bread", "1 × ₹45.00", "₹45.00", "₹45.00"),
                LineItem("Rice 5kg", "1 × ₹420.00", "₹420.00", "₹420.00"),
                LineItem("Sunflower Oil 1L", "2 × ₹175.00", "₹175.00", "₹350.00"),
                LineItem("Whole Wheat Flour 5kg", "1 × ₹225.00", "₹225.00", "₹225.00")
            )
        ),
        Receipt(
            id = "restaurant",
            merchant = "Restaurant ABC",
            location = "Koramangala",
            dateLabel = "Sep 27, 2026",
            timeLabel = "08:30 PM",
            category = ReceiptCategory.Food,
            amount = "₹540.00",
            confidencePercent = 99,
            itemsCount = 3,
            paymentMethod = "Card",
            receiptNumber = "INV-2026-77401"
        ),
        Receipt(
            id = "oil",
            merchant = "Indian Oil",
            location = "Outer Ring Road",
            dateLabel = "Sep 25, 2026",
            timeLabel = "10:12 AM",
            category = ReceiptCategory.Fuel,
            amount = "₹2,000.00",
            confidencePercent = 94,
            itemsCount = 1,
            paymentMethod = "UPI",
            receiptNumber = "INV-2026-55112"
        ),
        Receipt(
            id = "croma",
            merchant = "Croma Electronics",
            location = "Forum Mall",
            dateLabel = "Sep 18, 2026",
            timeLabel = "06:45 PM",
            category = ReceiptCategory.Shopping,
            amount = "₹1,499.00",
            confidencePercent = 96,
            itemsCount = 2,
            paymentMethod = "Card",
            receiptNumber = "INV-2026-33018"
        ),
        Receipt(
            id = "medplus",
            merchant = "MedPlus Pharmacy",
            location = "Sector 6",
            dateLabel = "Sep 12, 2026",
            timeLabel = "11:20 AM",
            category = ReceiptCategory.Health,
            amount = "₹380.00",
            confidencePercent = 97,
            itemsCount = 4,
            paymentMethod = "Cash",
            receiptNumber = "INV-2026-11990"
        )
    )

    fun recentReceipts(limit: Int = 3): List<Receipt> = receipts.take(limit)

    fun findById(id: String): Receipt = receipts.firstOrNull { it.id == id } ?: receipts.first()

    val processingSteps = listOf(
        ProcessingStep(
            title = "Receipt captured",
            subtitle = "Sharp edge boundaries detected · 0.2s",
            status = StepStatus.Done
        ),
        ProcessingStep(
            title = "Extracting text blocks",
            subtitle = "42 words parsed with ML Kit · 0.4s",
            status = StepStatus.Done
        ),
        ProcessingStep(
            title = "Understanding receipt",
            subtitle = "Categorizing taxes & line items",
            status = StepStatus.Active
        ),
        ProcessingStep(
            title = "Structuring expense",
            subtitle = "Generate editable review sheet",
            status = StepStatus.Queued
        )
    )
}
