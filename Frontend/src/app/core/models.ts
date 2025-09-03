export enum KycStatus {
  ACTIVE = 'ACTIVE',
  INACTIVE = 'INACTIVE',
  PENDING = 'PENDING',
  REJECTED = 'REJECTED',
}
export enum AccountType {
  SAVINGS = 'SAVINGS',
  CURRENT = 'CURRENT',
}
export enum Gender {
  MALE = 'MALE',
  FEMALE = 'FEMALE',
  OTHERS = 'OTHERS',
}
export enum BankAccountType {
  CURRENT = 'CURRENT',
  SAVINGS = 'SAVINGS',
  SALARY = 'SALARY',
}
export enum PaymentStatus {
  PENDING = 'PENDING',
  SUCCESS = 'SUCCESS',
  FAILED = 'FAILED',
  CANCELLED = 'CANCELLED',
}

export interface Customer {
  id?: number;
  name: string;
  email: string;
  phone: string;
  address: string;
  aadhaar?: string;
  pan?: string;
  age?: number;
  accountNumber?: string;
  gender?: Gender;
  kycStatus?: KycStatus;
  accountType?: AccountType;
}
export interface Account {
  id: number;
  username: string;
  pan: any;
  aadhaar: any;
  accNo: string;
  bankType: BankAccountType;
  balance: number;
  loan: number;
  address: string;
}
export interface Payment {
  paymentId?: number;
  senderName?: string;
  senderAccountNumber?: string;
  receiverName?: string;
  receiverAccountNumber?: string;
  amount: number;
  paymentStatus?: PaymentStatus;
}
export interface PaymentRequest {
  senderId: number;
  receiverId: number;
  amount: number;
}
export interface Feedback {
  id?: number;
  name: string;
  email: string;
  message: string;
}
export interface AuditLog {
  id?: number;
  serviceName?: string;
  action?: string;
  status?: string;
  referenceId?: number;
  userId?: number;
  amount?: number;
  remarks?: string;
  createdAt?: string;
}
export interface Page<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
}
 

export enum UserRole {
  ADMIN = 'ADMIN',
  USER = 'USER'
}

export interface User {
  id?: number;       
  name: string;
  email: string;
  password: string;
  userRole: UserRole;
}
