const OrganChain = artifacts.require("OrganChain");

module.exports = function(deployer, network, accounts) {
  // Use first 3 accounts from Ganache as initial admins
  const initialAdmins = [accounts[0], accounts[1], accounts[2]];
  deployer.deploy(OrganChain, initialAdmins);
};
