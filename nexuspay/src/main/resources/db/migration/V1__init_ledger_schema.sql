-- 1. Счета пользователей и мерчантов
CREATE TABLE accounts (
    id              UUID PRIMARY KEY,
    owner_id        UUID NOT NULL,
    currency        VARCHAR(3) NOT NULL,
    balance         NUMERIC(19, 4) NOT NULL DEFAULT 0.0000,
    version         BIGINT NOT NULL DEFAULT 0,
    status          VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at      TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT chk_accounts_balance_non_negative CHECK (balance >= 0),
    CONSTRAINT chk_accounts_currency_len CHECK (char_length(currency) = 3)
);

CREATE INDEX idx_accounts_owner_id ON accounts (owner_id);

-- 2. Транзакции переводов (с поддержкой Idempotency-Key)
CREATE TABLE transfers (
    id              UUID PRIMARY KEY,
    idempotency_key VARCHAR(128) NOT NULL,
    from_account_id UUID NOT NULL REFERENCES accounts (id),
    to_account_id   UUID NOT NULL REFERENCES accounts (id),
    amount          NUMERIC(19, 4) NOT NULL,
    currency        VARCHAR(3) NOT NULL,
    status          VARCHAR(20) NOT NULL,
    created_at      TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT uq_transfers_idempotency_key UNIQUE (idempotency_key),
    CONSTRAINT chk_transfers_amount_positive CHECK (amount > 0),
    CONSTRAINT chk_transfers_different_accounts CHECK (from_account_id <> to_account_id)
);

CREATE INDEX idx_transfers_from_account_created ON transfers (from_account_id, created_at DESC);
CREATE INDEX idx_transfers_to_account_created ON transfers (to_account_id, created_at DESC);

-- 3. Неизменяемый журнал двойной записи (Double-Entry Ledger)
CREATE TABLE ledger_entries (
    id              BIGSERIAL PRIMARY KEY,
    transfer_id     UUID NOT NULL REFERENCES transfers (id),
    account_id      UUID NOT NULL REFERENCES accounts (id),
    direction       VARCHAR(6) NOT NULL, -- 'DEBIT' или 'CREDIT'
    amount          NUMERIC(19, 4) NOT NULL, -- отрицательное для DEBIT, положительное для CREDIT
    created_at      TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT chk_ledger_direction CHECK (direction IN ('DEBIT', 'CREDIT'))
);

CREATE INDEX idx_ledger_entries_account_created ON ledger_entries (account_id, created_at DESC);
CREATE INDEX idx_ledger_entries_transfer_id ON ledger_entries (transfer_id);
