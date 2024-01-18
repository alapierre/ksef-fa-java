package io.alapierre.gobl.core.signature;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2024.01.18
 */
public record KeyPairHolder<PR extends PrivateKey, PU extends PublicKey>(PR privateKey, PU publicKey) {

}
