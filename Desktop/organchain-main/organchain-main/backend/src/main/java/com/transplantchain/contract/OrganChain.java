package com.transplantchain.contract;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.10.0.
 */
@SuppressWarnings("rawtypes")
public class OrganChain extends Contract {
    public static final String BINARY = "0x60806040523480156200001157600080fd5b506040516200103c3803806200103c8339810160408190526200003491620001ad565b600381511015620000b15760405162461bcd60e51b815260206004820152603360248201527f52657175697265206174206c65617374203320696e697469616c2061646d696e60448201527f7320666f7220322f33206d756c74692d73696700000000000000000000000000606482015260840160405180910390fd5b60005b815181101562000172576000828281518110620000d557620000d56200027f565b60209081029190910181015182546001808201855560009485529284200180546001600160a01b0319166001600160a01b039092169190911790558351909182918590859081106200012b576200012b6200027f565b6020908102919091018101516001600160a01b03168252810191909152604001600020805460ff191691151591909117905580620001698162000295565b915050620000b4565b5050620002bd565b634e487b7160e01b600052604160045260246000fd5b80516001600160a01b0381168114620001a857600080fd5b919050565b60006020808385031215620001c157600080fd5b82516001600160401b0380821115620001d957600080fd5b818501915085601f830112620001ee57600080fd5b8151818111156200020357620002036200017a565b8060051b604051601f19603f830116810181811085821117156200022b576200022b6200017a565b6040529182528482019250838101850191888311156200024a57600080fd5b938501935b828510156200027357620002638562000190565b845293850193928501926200024f565b98975050505050505050565b634e487b7160e01b600052603260045260246000fd5b600060018201620002b657634e487b7160e01b600052601160045260246000fd5b5060010190565b610d6f80620002cd6000396000f3fe608060405234801561001057600080fd5b50600436106100935760003560e01c806360aead901161006657806360aead90146101275780638ed32f311461013a578063b7f9291814610160578063c47f2cb814610183578063c7ff15841461018b57600080fd5b806314bfd6d0146100985780631a742199146100c857806324d7806c146100df5780632de7ad5414610112575b600080fd5b6100ab6100a6366004610919565b610198565b6040516001600160a01b0390911681526020015b60405180910390f35b6100d160035481565b6040519081526020016100bf565b6101026100ed366004610932565b60016020526000908152604090205460ff1681565b60405190151581526020016100bf565b610125610120366004610978565b6101c2565b005b610125610135366004610a39565b610466565b61014d610148366004610919565b61063f565b6040516100bf9796959493929190610b32565b61010261016e366004610932565b60046020526000908152604090205460ff1681565b6101256107a1565b6002546101029060ff1681565b600081815481106101a857600080fd5b6000918252602090912001546001600160a01b0316905081565b3360009081526001602052604090205460ff166102155760405162461bcd60e51b815260206004820152600c60248201526b2737ba1030b71030b236b4b760a11b60448201526064015b60405180910390fd5b60025460ff16156102685760405162461bcd60e51b815260206004820152601c60248201527f436f6e74726163742069732063757272656e746c792068616c74656400000000604482015260640161020c565b60008381526005602052604081205490036102c55760405162461bcd60e51b815260206004820152601b60248201527f446f6e6f7220706c6564676520646f6573206e6f742065786973740000000000604482015260640161020c565b60008281526005602052604081205490036103225760405162461bcd60e51b815260206004820152601f60248201527f526563697069656e7420706c6564676520646f6573206e6f7420657869737400604482015260640161020c565b6000838152600560208190526040909120015460ff16156103855760405162461bcd60e51b815260206004820152601b60248201527f446f6e6f72206f7267616e20616c7265616479206d6174636865640000000000604482015260640161020c565b600083815260056020526040812060030154600160ff8481169190911b9091161690036103f45760405162461bcd60e51b815260206004820152601f60248201527f446f6e6f7220646964206e6f7420706c656467652074686973206f7267616e00604482015260640161020c565b600083815260056020818152604080842083018054600160ff1991821681179092558786529482902090930180549094169092179092555160ff83168152839185917fdc13531d12b1a8eb874e47adf454cee79c6db5dd905eb0fc4fab87832a364092910160405180910390a3505050565b60025460ff16156104b95760405162461bcd60e51b815260206004820152601c60248201527f436f6e74726163742069732063757272656e746c792068616c74656400000000604482015260640161020c565b600085815260056020526040902054156105265760405162461bcd60e51b815260206004820152602860248201527f506c6564676520616c72656164792065786973747320666f7220746869732041604482015267084908240d0c2e6d60c31b606482015260840161020c565b6040805160e081018252868152602080820187815282840187815260ff8716606085015260808401869052600060a085018190523360c08601528a815260059093529390912082518155905160018201559151909190600282019061058b9082610c1a565b50606082015160038201805460ff191660ff909216919091179055608082015160048201906105ba9082610c1a565b5060a08201516005909101805460c0909301516001600160a01b031661010002610100600160a81b0319921515929092166001600160a81b03199093169290921717905560405185907f632434f41b025a49d45efab69359ee1939ecbdba58b1d3e156883806426b525d90610630908690610cda565b60405180910390a25050505050565b6005602052600090815260409020805460018201546002830180549293919261066790610b91565b80601f016020809104026020016040519081016040528092919081815260200182805461069390610b91565b80156106e05780601f106106b5576101008083540402835291602001916106e0565b820191906000526020600020905b8154815290600101906020018083116106c357829003601f168201915b5050506003840154600485018054949560ff90921694919350915061070490610b91565b80601f016020809104026020016040519081016040528092919081815260200182805461073090610b91565b801561077d5780601f106107525761010080835404028352916020019161077d565b820191906000526020600020905b81548152906001019060200180831161076057829003601f168201915b5050506005909301549192505060ff8116906001600160a01b036101009091041687565b3360009081526001602052604090205460ff166107ef5760405162461bcd60e51b815260206004820152600c60248201526b2737ba1030b71030b236b4b760a11b604482015260640161020c565b60025460ff16156108335760405162461bcd60e51b815260206004820152600e60248201526d105b1c9958591e481a185b1d195960921b604482015260640161020c565b3360009081526004602052604090205460ff16156108935760405162461bcd60e51b815260206004820152601b60248201527f41646d696e20616c726561647920766f74656420746f2068616c740000000000604482015260640161020c565b336000908152600460205260408120805460ff1916600117905560038054916108bb83610d03565b90915550506000546108ce906002610d1c565b600380546108db91610d1c565b10610917576002805460ff191660011790556040517f1165ec9cbb558e06dfbbae71f88ee17122a228585e06b7791ef51fc182813aa990600090a15b565b60006020828403121561092b57600080fd5b5035919050565b60006020828403121561094457600080fd5b81356001600160a01b038116811461095b57600080fd5b9392505050565b803560ff8116811461097357600080fd5b919050565b60008060006060848603121561098d57600080fd5b83359250602084013591506109a460408501610962565b90509250925092565b634e487b7160e01b600052604160045260246000fd5b600067ffffffffffffffff808411156109de576109de6109ad565b604051601f8501601f19908116603f01168101908282118183101715610a0657610a066109ad565b81604052809350858152868686011115610a1f57600080fd5b858560208301376000602087830101525050509392505050565b600080600080600060a08688031215610a5157600080fd5b8535945060208601359350604086013567ffffffffffffffff80821115610a7757600080fd5b818801915088601f830112610a8b57600080fd5b610a9a898335602085016109c3565b9450610aa860608901610962565b93506080880135915080821115610abe57600080fd5b508601601f81018813610ad057600080fd5b610adf888235602084016109c3565b9150509295509295909350565b6000815180845260005b81811015610b1257602081850181015186830182015201610af6565b506000602082860101526020601f19601f83011685010191505092915050565b87815286602082015260e060408201526000610b5160e0830188610aec565b60ff871660608401528281036080840152610b6c8187610aec565b94151560a084015250506001600160a01b039190911660c09091015295945050505050565b600181811c90821680610ba557607f821691505b602082108103610bc557634e487b7160e01b600052602260045260246000fd5b50919050565b601f821115610c1557600081815260208120601f850160051c81016020861015610bf25750805b601f850160051c820191505b81811015610c1157828155600101610bfe565b5050505b505050565b815167ffffffffffffffff811115610c3457610c346109ad565b610c4881610c428454610b91565b84610bcb565b602080601f831160018114610c7d5760008415610c655750858301515b600019600386901b1c1916600185901b178555610c11565b600085815260208120601f198616915b82811015610cac57888601518255948401946001909101908401610c8d565b5085821015610cca5787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b60208152600061095b6020830184610aec565b634e487b7160e01b600052601160045260246000fd5b600060018201610d1557610d15610ced565b5060010190565b8082028115828204841417610d3357610d33610ced565b9291505056fea264697066735822122008e95543dabb5585331dbb24c9d98a659df208e1cb1f2ba9953a7c333f6ac98864736f6c63430008130033";

    public static final String FUNC_ADMINS = "admins";

    public static final String FUNC_HALTSIGNATURES = "haltSignatures";

    public static final String FUNC_HASVOTEDHALT = "hasVotedHalt";

    public static final String FUNC_ISADMIN = "isAdmin";

    public static final String FUNC_ISHALTED = "isHalted";

    public static final String FUNC_PLEDGES = "pledges";

    public static final String FUNC_REGISTERPLEDGE = "registerPledge";

    public static final String FUNC_EXECUTEMATCH = "executeMatch";

    public static final String FUNC_VOTEHALT = "voteHalt";

    public static final Event CONTRACTHALTED_EVENT = new Event("ContractHalted", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event MATCHEXECUTED_EVENT = new Event("MatchExecuted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Bytes32>(true) {}, new TypeReference<Uint8>() {}));
    ;

    public static final Event PLEDGEREGISTERED_EVENT = new Event("PledgeRegistered", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected OrganChain(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected OrganChain(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected OrganChain(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected OrganChain(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ContractHaltedEventResponse> getContractHaltedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(CONTRACTHALTED_EVENT, transactionReceipt);
        ArrayList<ContractHaltedEventResponse> responses = new ArrayList<ContractHaltedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ContractHaltedEventResponse typedResponse = new ContractHaltedEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ContractHaltedEventResponse getContractHaltedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(CONTRACTHALTED_EVENT, log);
        ContractHaltedEventResponse typedResponse = new ContractHaltedEventResponse();
        typedResponse.log = log;
        return typedResponse;
    }

    public Flowable<ContractHaltedEventResponse> contractHaltedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getContractHaltedEventFromLog(log));
    }

    public Flowable<ContractHaltedEventResponse> contractHaltedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CONTRACTHALTED_EVENT));
        return contractHaltedEventFlowable(filter);
    }

    public static List<MatchExecutedEventResponse> getMatchExecutedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(MATCHEXECUTED_EVENT, transactionReceipt);
        ArrayList<MatchExecutedEventResponse> responses = new ArrayList<MatchExecutedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MatchExecutedEventResponse typedResponse = new MatchExecutedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.donorHash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.recipientHash = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.organId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static MatchExecutedEventResponse getMatchExecutedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(MATCHEXECUTED_EVENT, log);
        MatchExecutedEventResponse typedResponse = new MatchExecutedEventResponse();
        typedResponse.log = log;
        typedResponse.donorHash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.recipientHash = (byte[]) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.organId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<MatchExecutedEventResponse> matchExecutedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getMatchExecutedEventFromLog(log));
    }

    public Flowable<MatchExecutedEventResponse> matchExecutedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MATCHEXECUTED_EVENT));
        return matchExecutedEventFlowable(filter);
    }

    public static List<PledgeRegisteredEventResponse> getPledgeRegisteredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PLEDGEREGISTERED_EVENT, transactionReceipt);
        ArrayList<PledgeRegisteredEventResponse> responses = new ArrayList<PledgeRegisteredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PledgeRegisteredEventResponse typedResponse = new PledgeRegisteredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.abhaHash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.ipfsCid = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static PledgeRegisteredEventResponse getPledgeRegisteredEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PLEDGEREGISTERED_EVENT, log);
        PledgeRegisteredEventResponse typedResponse = new PledgeRegisteredEventResponse();
        typedResponse.log = log;
        typedResponse.abhaHash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.ipfsCid = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<PledgeRegisteredEventResponse> pledgeRegisteredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getPledgeRegisteredEventFromLog(log));
    }

    public Flowable<PledgeRegisteredEventResponse> pledgeRegisteredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PLEDGEREGISTERED_EVENT));
        return pledgeRegisteredEventFlowable(filter);
    }

    public RemoteFunctionCall<String> admins(BigInteger param0) {
        final Function function = new Function(FUNC_ADMINS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> haltSignatures() {
        final Function function = new Function(FUNC_HALTSIGNATURES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> hasVotedHalt(String param0) {
        final Function function = new Function(FUNC_HASVOTEDHALT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isAdmin(String param0) {
        final Function function = new Function(FUNC_ISADMIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isHalted() {
        final Function function = new Function(FUNC_ISHALTED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Tuple7<byte[], byte[], String, BigInteger, byte[], Boolean, String>> pledges(byte[] param0) {
        final Function function = new Function(FUNC_PLEDGES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<Bool>() {}, new TypeReference<Address>() {}));
        return new RemoteFunctionCall<Tuple7<byte[], byte[], String, BigInteger, byte[], Boolean, String>>(function,
                new Callable<Tuple7<byte[], byte[], String, BigInteger, byte[], Boolean, String>>() {
                    @Override
                    public Tuple7<byte[], byte[], String, BigInteger, byte[], Boolean, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<byte[], byte[], String, BigInteger, byte[], Boolean, String>(
                                (byte[]) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (byte[]) results.get(4).getValue(), 
                                (Boolean) results.get(5).getValue(), 
                                (String) results.get(6).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> registerPledge(byte[] _abhaHash, byte[] _documentHash, String _ipfsCid, BigInteger _organBitmap, byte[] _witnessSignature) {
        final Function function = new Function(
                FUNC_REGISTERPLEDGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_abhaHash), 
                new org.web3j.abi.datatypes.generated.Bytes32(_documentHash), 
                new org.web3j.abi.datatypes.Utf8String(_ipfsCid), 
                new org.web3j.abi.datatypes.generated.Uint8(_organBitmap), 
                new org.web3j.abi.datatypes.DynamicBytes(_witnessSignature)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> executeMatch(byte[] _donorHash, byte[] _recipientHash, BigInteger _organId) {
        final Function function = new Function(
                FUNC_EXECUTEMATCH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_donorHash), 
                new org.web3j.abi.datatypes.generated.Bytes32(_recipientHash), 
                new org.web3j.abi.datatypes.generated.Uint8(_organId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> voteHalt() {
        final Function function = new Function(
                FUNC_VOTEHALT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static OrganChain load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new OrganChain(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static OrganChain load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new OrganChain(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static OrganChain load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new OrganChain(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static OrganChain load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new OrganChain(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<OrganChain> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, List<String> _initialAdmins) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(_initialAdmins, org.web3j.abi.datatypes.Address.class))));
        return deployRemoteCall(OrganChain.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<OrganChain> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, List<String> _initialAdmins) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(_initialAdmins, org.web3j.abi.datatypes.Address.class))));
        return deployRemoteCall(OrganChain.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<OrganChain> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, List<String> _initialAdmins) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(_initialAdmins, org.web3j.abi.datatypes.Address.class))));
        return deployRemoteCall(OrganChain.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<OrganChain> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, List<String> _initialAdmins) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(_initialAdmins, org.web3j.abi.datatypes.Address.class))));
        return deployRemoteCall(OrganChain.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class ContractHaltedEventResponse extends BaseEventResponse {
    }

    public static class MatchExecutedEventResponse extends BaseEventResponse {
        public byte[] donorHash;

        public byte[] recipientHash;

        public BigInteger organId;
    }

    public static class PledgeRegisteredEventResponse extends BaseEventResponse {
        public byte[] abhaHash;

        public String ipfsCid;
    }
}
