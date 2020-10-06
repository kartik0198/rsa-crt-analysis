# CryptoProjectRSA
The project was made as part of a research and analysis study conducted to understand the difference between RSA & RSA-CRT and finally infer which is the better one.

Implemented the standard RSA algorithm,
understanding its deficiency and to further optimize it through combining the RSA algorithm with Chinese
Remainder Theorem to speed up RSA decryption by an
approximate factor of four. Both the implementations are done in Java.

We then compare and analyse the performance of the variant for which graphs have been attached.

The RSA-CRT variant is backwards compatible in the sense that a
system using it can inter-operate with systems using standard RSA.

In future, a GUI based tool to encrypt/decrypt plain text will be made.

Also, check out the presentation and detailed report for this analysis.

<p align="center">
<img src="https://github.com/kartik0198/rsa-crt-analysis/blob/master/images/decryption.png"/>
<img src="https://github.com/kartik0198/rsa-crt-analysis/blob/master/images/keygen.png"/>
</p>
