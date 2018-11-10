package com.opensoul.sobiray.server.contract.api;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple9;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class Sobiray extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b506128eb806100206000396000f3006080604052600436106100e55763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630952272781146100ea5780630b41e2f71461010c57806313628b6d1461012c57806356344cc11461014c5780638dbd9ca214610184578063941e1234146101a65780639812db92146101c65780639faeb066146101e6578063a6c62d2f14610216578063ac71f11614610236578063b7a6e49f14610263578063e190206314610283578063f31e9e93146102b8578063f4af9ca7146102d8578063f6662dc1146102f8578063fafaaf4314610318575b600080fd5b3480156100f657600080fd5b5061010a610105366004612416565b610338565b005b34801561011857600080fd5b5061010a610127366004612467565b6105ad565b34801561013857600080fd5b5061010a6101473660046124c6565b610624565b34801561015857600080fd5b5061016c6101673660046123d9565b610715565b60405161017b939291906127c3565b60405180910390f35b34801561019057600080fd5b50610199610989565b60405161017b91906126c1565b3480156101b257600080fd5b5061010a6101c13660046123d9565b610ad4565b3480156101d257600080fd5b5061010a6101e136600461255a565b610b21565b3480156101f257600080fd5b50610206610201366004612467565b610c28565b60405161017b9493929190612773565b34801561022257600080fd5b5061010a6102313660046123d9565b610ff9565b34801561024257600080fd5b506102566102513660046123d9565b611029565b60405161017b91906126d2565b34801561026f57600080fd5b5061010a61027e3660046123d9565b611284565b34801561028f57600080fd5b506102a361029e3660046123d9565b6112b0565b60405161017b999897969594939291906126e6565b3480156102c457600080fd5b5061010a6102d33660046123d9565b61155e565b3480156102e457600080fd5b5061010a6102f33660046123d9565b61158a565b34801561030457600080fd5b5061016c6103133660046123d9565b6115b6565b34801561032457600080fd5b506101996103333660046123d9565b611821565b60006103426121e0565b600091505b6000548210156105a757836040518082805190602001908083835b602083106103815780518252601f199092019160209182019101610362565b5181516020939093036101000a6000190180199091169216919091179052604051920182900390912060008054919450925085915081106103be57fe5b9060005260206000209060090201600101604051808280546001816001161561010002031660029004801561042a5780601f1061040857610100808354040283529182019161042a565b820191906000526020600020905b815481529060010190602001808311610416575b5050915050604051809103902060001916141561059c57600080548390811061044f57fe5b600091825260209091206040805161012081019091526009909202018054829060ff16600481111561047d57fe5b600481111561048857fe5b8152602001600182018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105255780601f106104fa57610100808354040283529160200191610525565b820191906000526020600020905b81548152906001019060200180831161050857829003601f168201915b505050505081526020016002820154815260200160038201548152602001600482015481526020016005820154815260200160068201548152602001600782015481526020016008820154815250509050828160000190600481111561058757fe5b9081600481111561059457fe5b9052506105a7565b600190910190610347565b50505050565b60008060006105bb856115b6565b919650935091506105cb85611029565b90506105d8858542611bd7565b6105e185610ad4565b60018160048111156105ef57fe5b14156105ff576105ff8584611f85565b600481600481111561060d57fe5b141561061d5761061d8583611f85565b5050505050565b6001836040518082805190602001908083835b602083106106565780518252601f199092019160209182019101610637565b51815160209384036101000a600019018019909216911617905292019485525060408051948590038201852060808601825287865285830189905260009186018290526060860187905280546001810180835591835291839020865180519297965060049093020193506106cd928492019061222d565b5060208281015180516106e6926001850192019061222d565b50604082015160028201556060820151805161070c91600384019160209091019061222d565b50505050505050565b606060008060006107246121e0565b600091505b60005482101561098057856040518082805190602001908083835b602083106107635780518252601f199092019160209182019101610744565b5181516020939093036101000a6000190180199091169216919091179052604051920182900390912060008054919450925085915081106107a057fe5b9060005260206000209060090201600101604051808280546001816001161561010002031660029004801561080c5780601f106107ea57610100808354040283529182019161080c565b820191906000526020600020905b8154815290600101906020018083116107f8575b5050915050604051809103902060001916141561097557600080548390811061083157fe5b600091825260209091206040805161012081019091526009909202018054829060ff16600481111561085f57fe5b600481111561086a57fe5b8152602001600182018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109075780601f106108dc57610100808354040283529160200191610907565b820191906000526020600020905b8154815290600101906020018083116108ea57829003601f168201915b505050505081526020016002820154815260200160038201548152602001600482015481526020016005820154815260200160068201548152602001600782015481526020016008820154815250509050806020015181604001518260600151829250945094509450610980565b600190910190610729565b50509193909250565b60606000805b6000548110156109fd57816000828154811015156109a957fe5b600091825260208083208454600181810180885596865292909420600990930201810180546109f394939093019290916002610100918316159190910260001901909116046122ab565b505060010161098f565b81805480602002602001604051908101604052809291908181526020016000905b82821015610ac95760008481526020908190208301805460408051601f6002600019610100600187161502019094169390930492830185900485028101850190915281815292830182828015610ab55780601f10610a8a57610100808354040283529160200191610ab5565b820191906000526020600020905b815481529060010190602001808311610a9857829003601f168201915b505050505081526020019060010190610a1e565b505050509250505090565b600080600080600080600080610ae989610715565b919a509750955042821015610b1657868610610b0d57610b088961155e565b610b16565b610b168961158a565b505050505050505050565b60408051610120810182526000808252602082018a9052918101889052606081018290526080810187905260a0810186905260c0810185905260e08101849052610100810183905281546001808201808555938052825160099092027f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e563018054909291839160ff191690836004811115610bb757fe5b02179055506020828101518051610bd4926001850192019061222d565b5060408201518160020155606082015181600301556080820151816004015560a0820151816005015560c0820151816006015560e08201518160070155610100820151816008015550505050505050505050565b60608060006060806000610c3a612320565b6001896040518082805190602001908083835b60208310610c6c5780518252601f199092019160209182019101610c4d565b51815160209384036101000a60001901801990921691161790529201948552506040805194859003820185208054808402870184019092528186529350915060009084015b82821015610eb35760008481526020908190206040805160048602909201805460026001821615610100026000190190911604601f8101859004909402830160a09081019092526080830184815292939092849290918491840182828015610d5a5780601f10610d2f57610100808354040283529160200191610d5a565b820191906000526020600020905b815481529060010190602001808311610d3d57829003601f168201915b50505050508152602001600182018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610dfc5780601f10610dd157610100808354040283529160200191610dfc565b820191906000526020600020905b815481529060010190602001808311610ddf57829003601f168201915b505050918352505060028281015460208084019190915260038401805460408051601f60001961010060018616150201909316959095049182018490048402850184018152818552909401939091830182828015610e9b5780601f10610e7057610100808354040283529160200191610e9b565b820191906000526020600020905b815481529060010190602001808311610e7e57829003601f168201915b50505050508152505081526020019060010190610cb1565b505050509250600091505b8251821015610fed57876040518082805190602001908083835b60208310610ef75780518252601f199092019160209182019101610ed8565b5181516020939093036101000a6000190180199091169216919091179052604051920182900390912086519093508692508591508110610f3357fe5b90602001906020020151600001516040518082805190602001908083835b60208310610f705780518252601f199092019160209182019101610f51565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019161415610fe2578282815181101515610fb557fe5b602090810290910181015180519181015160408201516060830151939a5090985096509094509050610fed565b600190910190610ebe565b50505092959194509250565b600061100482611029565b9050600081600481111561101457fe5b141561102557611025826001610338565b5050565b6000806110346121e0565b600091505b60005482101561127d57836040518082805190602001908083835b602083106110735780518252601f199092019160209182019101611054565b5181516020939093036101000a6000190180199091169216919091179052604051920182900390912060008054919450925085915081106110b057fe5b9060005260206000209060090201600101604051808280546001816001161561010002031660029004801561111c5780601f106110fa57610100808354040283529182019161111c565b820191906000526020600020905b815481529060010190602001808311611108575b5050915050604051809103902060001916141561127257600080548390811061114157fe5b600091825260209091206040805161012081019091526009909202018054829060ff16600481111561116f57fe5b600481111561117a57fe5b8152602001600182018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156112175780601f106111ec57610100808354040283529160200191611217565b820191906000526020600020905b8154815290600101906020018083116111fa57829003601f168201915b505050918352505060028201546020820152600382015460408201526004820154606082015260058201546080820152600682015460a0820152600782015460c082015260089091015460e09091015280519350905061127d565b600190910190611039565b5050919050565b600061128f82611029565b9050600181600481111561129f57fe5b141561102557611025826002610338565b600060606000806000806000806000806112c86121e0565b600091505b60005482101561154f578b6040518082805190602001908083835b602083106113075780518252601f1990920191602091820191016112e8565b5181516020939093036101000a60001901801990911692169190911790526040519201829003909120600080549194509250859150811061134457fe5b906000526020600020906009020160010160405180828054600181600116156101000203166002900480156113b05780601f1061138e5761010080835404028352918201916113b0565b820191906000526020600020905b81548152906001019060200180831161139c575b505091505060405180910390206000191614156115445760008054839081106113d557fe5b600091825260209091206040805161012081019091526009909202018054829060ff16600481111561140357fe5b600481111561140e57fe5b8152602001600182018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156114ab5780601f10611480576101008083540402835291602001916114ab565b820191906000526020600020905b81548152906001019060200180831161148e57829003601f168201915b505050505081526020016002820154815260200160038201548152602001600482015481526020016005820154815260200160068201548152602001600782015481526020016008820154815250509050806000015181602001518260400151836060015184608001518560a001518660c001518760e001518861010001518797509a509a509a509a509a509a509a509a509a5061154f565b6001909101906112cd565b50509193959799909294969850565b600061156982611029565b9050600181600481111561157957fe5b141561102557611025826004610338565b600061159582611029565b905060018160048111156115a557fe5b141561102557611025826003610338565b606060008060006115c56121e0565b600091505b60005482101561098057856040518082805190602001908083835b602083106116045780518252601f1990920191602091820191016115e5565b5181516020939093036101000a60001901801990911692169190911790526040519201829003909120600080549194509250859150811061164157fe5b906000526020600020906009020160010160405180828054600181600116156101000203166002900480156116ad5780601f1061168b5761010080835404028352918201916116ad565b820191906000526020600020905b815481529060010190602001808311611699575b505091505060405180910390206000191614156118165760008054839081106116d257fe5b600091825260209091206040805161012081019091526009909202018054829060ff16600481111561170057fe5b600481111561170b57fe5b8152602001600182018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156117a85780601f1061177d576101008083540402835291602001916117a8565b820191906000526020600020905b81548152906001019060200180831161178b57829003601f168201915b50505050508152602001600282015481526020016003820154815260200160048201548152602001600582015481526020016006820154815260200160078201548152602001600882015481525050905080602001518160a001518260c00151829250945094509450610980565b6001909101906115ca565b6060806000806001856040518082805190602001908083835b602083106118595780518252601f19909201916020918201910161183a565b51815160209384036101000a60001901801990921691161790529201948552506040805194859003820185208054808402870184019092528186529350915060009084015b82821015611aa05760008481526020908190206040805160048602909201805460026001821615610100026000190190911604601f8101859004909402830160a090810190925260808301848152929390928492909184918401828280156119475780601f1061191c57610100808354040283529160200191611947565b820191906000526020600020905b81548152906001019060200180831161192a57829003601f168201915b50505050508152602001600182018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156119e95780601f106119be576101008083540402835291602001916119e9565b820191906000526020600020905b8154815290600101906020018083116119cc57829003601f168201915b505050918352505060028281015460208084019190915260038401805460408051601f60001961010060018616150201909316959095049182018490048402850184018152818552909401939091830182828015611a885780601f10611a5d57610100808354040283529160200191611a88565b820191906000526020600020905b815481529060010190602001808311611a6b57829003601f168201915b5050505050815250508152602001906001019061189e565b505050509250600090505b8251811015611afd57818382815181101515611ac357fe5b6020908102909101810151518254600181018085556000948552938390208251611af3949190920192019061222d565b5050600101611aab565b81805480602002602001604051908101604052809291908181526020016000905b82821015611bc95760008481526020908190208301805460408051601f6002600019610100600187161502019094169390930492830185900485028101850190915281815292830182828015611bb55780601f10611b8a57610100808354040283529160200191611bb5565b820191906000526020600020905b815481529060010190602001808311611b9857829003601f168201915b505050505081526020019060010190611b1e565b505050509350505050919050565b60606000611be3612320565b6001866040518082805190602001908083835b60208310611c155780518252601f199092019160209182019101611bf6565b51815160209384036101000a60001901801990921691161790529201948552506040805194859003820185208054808402870184019092528186529350915060009084015b82821015611e5c5760008481526020908190206040805160048602909201805460026001821615610100026000190190911604601f8101859004909402830160a09081019092526080830184815292939092849290918491840182828015611d035780601f10611cd857610100808354040283529160200191611d03565b820191906000526020600020905b815481529060010190602001808311611ce657829003601f168201915b50505050508152602001600182018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015611da55780601f10611d7a57610100808354040283529160200191611da5565b820191906000526020600020905b815481529060010190602001808311611d8857829003601f168201915b505050918352505060028281015460208084019190915260038401805460408051601f60001961010060018616150201909316959095049182018490048402850184018152818552909401939091830182828015611e445780601f10611e1957610100808354040283529160200191611e44565b820191906000526020600020905b815481529060010190602001808311611e2757829003601f168201915b50505050508152505081526020019060010190611c5a565b505050509250600091505b8251821015611f7d57846040518082805190602001908083835b60208310611ea05780518252601f199092019160209182019101611e81565b5181516020939093036101000a6000190180199091169216919091179052604051920182900390912086519093508692508591508110611edc57fe5b90602001906020020151600001516040518082805190602001908083835b60208310611f195780518252601f199092019160209182019101611efa565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019161415611f72578282815181101515611f5e57fe5b602090810290910101516040810185905290505b600190910190611e67565b505050505050565b6000611f8f6121e0565b600091505b6000548210156105a757836040518082805190602001908083835b60208310611fce5780518252601f199092019160209182019101611faf565b5181516020939093036101000a60001901801990911692169190911790526040519201829003909120600080549194509250859150811061200b57fe5b906000526020600020906009020160010160405180828054600181600116156101000203166002900480156120775780601f10612055576101008083540402835291820191612077565b820191906000526020600020905b815481529060010190602001808311612063575b505091505060405180910390206000191614156121d557600080548390811061209c57fe5b600091825260209091206040805161012081019091526009909202018054829060ff1660048111156120ca57fe5b60048111156120d557fe5b8152602001600182018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156121725780601f1061214757610100808354040283529160200191612172565b820191906000526020600020905b81548152906001019060200180831161215557829003601f168201915b50505091835250506002820154602082015260038201546040820152600482015460608083019190915260058301546080830152600683015460a0830152600783015460c083015260089092015460e090910152810180518501905290506105a7565b600190910190611f94565b604080516101208101909152806000815260200160608152602001600081526020016000815260200160008152602001600081526020016000815260200160008152602001600081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061226e57805160ff191683800117855561229b565b8280016001018555821561229b579182015b8281111561229b578251825591602001919060010190612280565b506122a7929150612349565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106122e4578054855561229b565b8280016001018555821561229b57600052602060002091601f016020900482015b8281111561229b578254825591600101919060010190612305565b608060405190810160405280606081526020016060815260200160008152602001606081525090565b61236391905b808211156122a7576000815560010161234f565b90565b60006123728235612855565b9392505050565b6000601f8201831361238a57600080fd5b813561239d61239882612817565b6127f0565b915080825260208301602083018583830111156123b957600080fd5b6123c483828461286f565b50505092915050565b60006123728235612363565b6000602082840312156123eb57600080fd5b813567ffffffffffffffff81111561240257600080fd5b61240e84828501612379565b949350505050565b6000806040838503121561242957600080fd5b823567ffffffffffffffff81111561244057600080fd5b61244c85828601612379565b925050602061245d85828601612366565b9150509250929050565b6000806040838503121561247a57600080fd5b823567ffffffffffffffff81111561249157600080fd5b61249d85828601612379565b925050602083013567ffffffffffffffff8111156124ba57600080fd5b61245d85828601612379565b6000806000606084860312156124db57600080fd5b833567ffffffffffffffff8111156124f257600080fd5b6124fe86828701612379565b935050602084013567ffffffffffffffff81111561251b57600080fd5b61252786828701612379565b925050604084013567ffffffffffffffff81111561254457600080fd5b61255086828701612379565b9150509250925092565b600080600080600080600060e0888a03121561257557600080fd5b873567ffffffffffffffff81111561258c57600080fd5b6125988a828b01612379565b97505060206125a98a828b016123cd565b96505060406125ba8a828b016123cd565b95505060606125cb8a828b016123cd565b94505060806125dc8a828b016123cd565b93505060a06125ed8a828b016123cd565b92505060c06125fe8a828b016123cd565b91505092959891949750929550565b600061261882612845565b808452602084019350836020820285016126318561283f565b60005b8481101561266857838303885261264c838351612683565b92506126578261283f565b602098909801979150600101612634565b50909695505050505050565b61267d81612864565b82525050565b600061268e82612845565b8084526126a281602086016020860161287b565b6126ab816128a7565b9093016020019392505050565b61267d81612363565b60208082528101612372818461260d565b602081016126e08284612674565b92915050565b61012081016126f5828c612674565b8181036020830152612707818b612683565b9050612716604083018a6126b8565b61272360608301896126b8565b61273060808301886126b8565b61273d60a08301876126b8565b61274a60c08301866126b8565b61275760e08301856126b8565b6127656101008301846126b8565b9a9950505050505050505050565b608080825281016127848187612683565b905081810360208301526127988186612683565b90506127a760408301856126b8565b81810360608301526127b98184612683565b9695505050505050565b606080825281016127d48186612683565b90506127e360208301856126b8565b61240e60408301846126b8565b60405181810167ffffffffffffffff8111828210171561280f57600080fd5b604052919050565b600067ffffffffffffffff82111561282e57600080fd5b506020601f91909101601f19160190565b60200190565b5190565b6000600582106122a757fe5b6000600582106122a757600080fd5b60006126e082612849565b82818337506000910152565b60005b8381101561289657818101518382015260200161287e565b838111156105a75750506000910152565b601f01601f1916905600a265627a7a72305820467f48c4cc7d523bd2330c6ea48e70c78daf2a3af8b015297e5a34cc269dbb116c6578706572696d656e74616cf50037";

    public static final String FUNC_SETEVENTSTATUS = "setEventStatus";

    public static final String FUNC_GUESTPAID = "guestPaid";

    public static final String FUNC_ADDGUEST = "addGuest";

    public static final String FUNC_GETEVENTSUMS = "getEventSums";

    public static final String FUNC_GETEVENTIDS = "getEventIds";

    public static final String FUNC_CHECKENDPRESALE = "checkEndPresale";

    public static final String FUNC_ADDEVENT = "addEvent";

    public static final String FUNC_GETGUEST = "getGuest";

    public static final String FUNC_STARTPRESALE = "startPresale";

    public static final String FUNC_GETEVENTSTATUS = "getEventStatus";

    public static final String FUNC_CANCELPRESALE = "cancelPresale";

    public static final String FUNC_GETEVENT = "getEvent";

    public static final String FUNC_SUCCESSPRESALE = "successPresale";

    public static final String FUNC_FAILPRESALE = "failPresale";

    public static final String FUNC_GETEVENTPRICES = "getEventPrices";

    public static final String FUNC_GETEVENTGUESTSIDS = "getEventGuestsIds";

    protected Sobiray(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Sobiray(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> setEventStatus(String evId, BigInteger status) {
        final Function function = new Function(
                FUNC_SETEVENTSTATUS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(evId), 
                new org.web3j.abi.datatypes.generated.Uint8(status)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> guestPaid(String eventId, String guestId) {
        final Function function = new Function(
                FUNC_GUESTPAID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(eventId), 
                new org.web3j.abi.datatypes.Utf8String(guestId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addGuest(String eventId, String guestId, String transactionId) {
        final Function function = new Function(
                FUNC_ADDGUEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(eventId), 
                new org.web3j.abi.datatypes.Utf8String(guestId), 
                new org.web3j.abi.datatypes.Utf8String(transactionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple3<String, BigInteger, BigInteger>> getEventSums(String evId) {
        final Function function = new Function(FUNC_GETEVENTSUMS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(evId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple3<String, BigInteger, BigInteger>>(
                new Callable<Tuple3<String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<List> getEventIds() {
        final Function function = new Function(FUNC_GETEVENTIDS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<TransactionReceipt> checkEndPresale(String eventId) {
        final Function function = new Function(
                FUNC_CHECKENDPRESALE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(eventId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addEvent(String eventId, BigInteger successSum, BigInteger maxGuestsCount, BigInteger presalePrice, BigInteger salePrice, BigInteger fundingDeadline, BigInteger eventDate) {
        final Function function = new Function(
                FUNC_ADDEVENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(eventId), 
                new org.web3j.abi.datatypes.generated.Uint256(successSum), 
                new org.web3j.abi.datatypes.generated.Uint256(maxGuestsCount), 
                new org.web3j.abi.datatypes.generated.Uint256(presalePrice), 
                new org.web3j.abi.datatypes.generated.Uint256(salePrice), 
                new org.web3j.abi.datatypes.generated.Uint256(fundingDeadline), 
                new org.web3j.abi.datatypes.generated.Uint256(eventDate)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple4<String, String, BigInteger, String>> getGuest(String eventId, String guestId) {
        final Function function = new Function(FUNC_GETGUEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(eventId), 
                new org.web3j.abi.datatypes.Utf8String(guestId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple4<String, String, BigInteger, String>>(
                new Callable<Tuple4<String, String, BigInteger, String>>() {
                    @Override
                    public Tuple4<String, String, BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, String, BigInteger, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (String) results.get(3).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> startPresale(String eventId) {
        final Function function = new Function(
                FUNC_STARTPRESALE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(eventId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getEventStatus(String eventId) {
        final Function function = new Function(FUNC_GETEVENTSTATUS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(eventId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> cancelPresale(String eventId) {
        final Function function = new Function(
                FUNC_CANCELPRESALE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(eventId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple9<BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> getEvent(String evId) {
        final Function function = new Function(FUNC_GETEVENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(evId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple9<BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple9<BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple9<BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple9<BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue(), 
                                (BigInteger) results.get(7).getValue(), 
                                (BigInteger) results.get(8).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> successPresale(String eventId) {
        final Function function = new Function(
                FUNC_SUCCESSPRESALE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(eventId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> failPresale(String eventId) {
        final Function function = new Function(
                FUNC_FAILPRESALE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(eventId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple3<String, BigInteger, BigInteger>> getEventPrices(String evId) {
        final Function function = new Function(FUNC_GETEVENTPRICES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(evId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple3<String, BigInteger, BigInteger>>(
                new Callable<Tuple3<String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<List> getEventGuestsIds(String eventId) {
        final Function function = new Function(FUNC_GETEVENTGUESTSIDS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(eventId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public static RemoteCall<Sobiray> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Sobiray.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Sobiray> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Sobiray.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static Sobiray load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Sobiray(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Sobiray load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Sobiray(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
