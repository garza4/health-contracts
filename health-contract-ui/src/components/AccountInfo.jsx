const AccountInfo = ({publicAccountNumber,balance,...props}) => {

    return(
        <>
            <header>
                Account {publicAccountNumber?publicAccountNumber:"No info"}
                <p>
                    Balance : {balance?balance:"No info"}
                </p>
            </header>
        </>
    )
}

export default AccountInfo;