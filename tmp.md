| Annotation            | Purpose                            | Example Aspect Type |
| --------------------- | ---------------------------------- | ------------------- |
| `@AuditTrail`         | Log who did what                   | `@AfterReturning`   |
| `@ValidateAccess`     | Check authorization                | `@Before`           |
| `@TrackApiCall`       | Log API hit and payload            | `@Around`           |
| `@HandleException`    | Catch exceptions globally          | `@AfterThrowing`    |
| `@RetryableOperation` | Retry logic for transient failures | `@Around`           |
